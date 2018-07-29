package be.icc.controller;

import be.icc.entity.RepresentationUserEntity;
import be.icc.entity.RepresentationsEntity;
import be.icc.entity.ShowsEntity;
import be.icc.entity.UsersEntity;
import be.icc.repository.CategoryRepository;
import be.icc.repository.RepresentationRepository;
import be.icc.repository.RepresentationUserRepository;
import be.icc.repository.ShowRepository;
import be.icc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShowController {

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RepresentationRepository representationRepository;
    @Autowired
    private RepresentationUserRepository representationUserRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/shows")
    public ModelAndView shows(ModelAndView modelAndView) {

        List<ShowsEntity> shows = (List<ShowsEntity>) showRepository.findAll();
        modelAndView.addObject("shows", shows);
        modelAndView.addObject("categoriesList", categoryRepository.findAll());
        modelAndView.setViewName("shows");

        return modelAndView;
    }

    @GetMapping("/showsParCategorie")
    public ModelAndView showsParCategorie(ModelAndView modelAndView) {

        modelAndView.addObject("categories", categoryRepository.findAll());
        modelAndView.setViewName("showsParCategorie");

        return modelAndView;
    }

    @GetMapping("/showsParCategorie/{name}")
    public String showsByCategoryId(@PathVariable("name")String name, Model model) {

        model.addAttribute("shows", showRepository.findByCategory(categoryRepository.findByNom(name)));
        model.addAttribute("categoriesList", categoryRepository.findAll());

        return "shows";
    }

    @RequestMapping(value = "searchShow", method = RequestMethod.GET)
    public String showByName(Model model, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "nomCategory", required = false) String nomCategory) {
        if(name != null && !"".equals(name)) {

            if (nomCategory != null && !"".equals(nomCategory)) {
                model.addAttribute("shows", showRepository.findByTitleAndCategory(name, categoryRepository.findByNom(nomCategory)));
            } else {
                model.addAttribute("shows", showRepository.findByTitle(name));
            }
        } else if(nomCategory != null && !"".equals(nomCategory)){
            model.addAttribute("shows", showRepository.findByCategory(categoryRepository.findByNom(nomCategory)));
        }
        return "shows";
    }

    @RequestMapping(value = "searchShowByCategory", method = RequestMethod.GET)
    public String showByCategory(Model model, @RequestParam(value = "nomCategory", required = false) String category) {
        model.addAttribute("shows", showRepository.findByCategory(categoryRepository.findByNom(category)));
        return "shows";
    }

    @RequestMapping(value = "/bookingShow", method = RequestMethod.GET)
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @RequestParam(value = "idRepresentation", required = true) Long idRepresentation, Principal principal) {
        UsersEntity user = userRepository.findByLogin(principal.getName());
        RepresentationsEntity representation = representationRepository.findOne(idRepresentation);
        RepresentationUserEntity representationUserEntity = new RepresentationUserEntity();
        representationUserEntity.setPlaces(1);
        representationUserEntity.setUsersByUserId(user);
        representationUserEntity.setRepresentationsByRepresentationId(representation);
        representationUserRepository.save(representationUserEntity);
        modelAndView.setViewName("validationBooking");
        return modelAndView;
    }

    @GetMapping("/reservations")
    public ModelAndView reservations(ModelAndView modelAndView, Principal principal) {
        UsersEntity user = userRepository.findByLogin(principal.getName());

        List<RepresentationUserEntity> representationUsers =  representationUserRepository.findByUsersByUserId(user);
        ArrayList<RepresentationsEntity> representations = new ArrayList<RepresentationsEntity>();
        for(RepresentationUserEntity reservation : representationUsers) {
            representations.add(representationRepository.findByRepresentationUsers(reservation));
        }
        modelAndView.addObject("representations", representations);

        modelAndView.setViewName("reservations");

        return modelAndView;
    }

    @GetMapping(value = "/newShow")
    public ModelAndView newShow(ModelAndView modelAndView, RepresentationsEntity representation) {
        modelAndView.setViewName("/newShow");
        modelAndView.addObject("representation", representation);
        modelAndView.addObject("categoriesList", categoryRepository.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/addRepresentation", method = RequestMethod.POST)
    public ModelAndView addPresentation(ModelAndView modelAndView, RepresentationsEntity representation, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("newShow");
        } else {
            representationRepository.save(representation);

            modelAndView.addObject("representation",representation);
            modelAndView.addObject("confirmationMessage", "La representation a bien ete cree");
            modelAndView.setViewName("newShow");
        }

        return modelAndView;
    }
}
