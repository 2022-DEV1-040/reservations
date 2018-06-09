package be.icc.controller;

import be.icc.entity.UsersEntity;
import be.icc.repository.RoleRepository;
import be.icc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


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
            modelAndView.addObject("alreadyRegisteredMessage", "Ce login est déja utilisé par un autre utilisateur");
            modelAndView.setViewName("register");
            bindingResult.reject("login");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("register");
        } else { // l'utilisateur est inscrit
            user.setRolesByRoleId(roleRepository.findByRole("membre"));
            user.setLangue("FR");
            userRepository.save(user);

            String appUrl = request.getScheme() + "://" + request.getServerName();

            modelAndView.addObject("confirmationMessage", "Vous êtes bien inscrit sur le site");
            modelAndView.setViewName("register");
        }

        return modelAndView;
    }
}
