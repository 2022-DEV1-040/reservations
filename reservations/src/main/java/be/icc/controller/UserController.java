package be.icc.controller;

import be.icc.entity.UsersEntity;
import be.icc.repository.RoleRepository;
import be.icc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Return registration form template
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, UsersEntity user){
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    // Process form input data
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, UsersEntity user, BindingResult bindingResult, HttpServletRequest request) {

        // On vérifie que le login est unique
        UsersEntity userExists = userRepository.findByLogin(user.getLogin());

        System.out.println(userExists);

        if (userExists != null) {
            modelAndView.addObject("errorMessage", "Ce login est déja utilisé par un autre utilisateur");
            modelAndView.setViewName("register");
            modelAndView.addObject("user",user);
            bindingResult.reject("login");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("register");
        } else { // l'utilisateur est inscrit
            user.setRolesByRoleId(roleRepository.findByRole("membre"));
            user.setLangue("FR");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            modelAndView.addObject("user",user);
            modelAndView.addObject("confirmationMessage", "Vous etes bien inscrit sur le site");
            modelAndView.setViewName("login");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        UsersEntity loginedUser = userRepository.findByLogin(principal.getName());

        model.addAttribute("user", loginedUser);

        return "userInfo";
    }

    // Process form input data
    @RequestMapping(value = "/modifyProfil", method = RequestMethod.POST)
    public ModelAndView processModifyForm(ModelAndView modelAndView, UsersEntity user, BindingResult bindingResult, HttpServletRequest request, Principal principal) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("userInfo");
        } else {
            UsersEntity loginedUser = userRepository.findByLogin(principal.getName());
            loginedUser.setEmail(user.getEmail());
            loginedUser.setFirstname(user.getFirstname());
            loginedUser.setLastname(user.getLastname());
            userRepository.save(loginedUser);

            modelAndView.addObject("user",user);
            modelAndView.addObject("confirmationMessage", "Votre profil a ete mis a jour");
            modelAndView.setViewName("userInfo");
        }

        return modelAndView;
    }
}
