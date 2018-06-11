package be.icc.controller;

import be.icc.entity.RepresentationUserEntity;
import be.icc.entity.RepresentationsEntity;
import be.icc.entity.ShowsEntity;
import be.icc.entity.UsersEntity;
import be.icc.repository.RepresentationRepository;
import be.icc.repository.RepresentationUserRepository;
import be.icc.repository.ShowRepository;
import be.icc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/shows")
    public ModelAndView shows(ModelAndView modelAndView) {

        List<ShowsEntity> shows = (List<ShowsEntity>) showRepository.findAll();
        modelAndView.addObject("shows", shows);
        modelAndView.setViewName("shows");

        return modelAndView;
    }

    @RequestMapping(value = "searchShow", method = RequestMethod.GET)
    public String showByName(Model model, @RequestParam(value = "name", required = false) String name) {
        model.addAttribute("shows", showRepository.findByTitle(name));
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
            representations.add(representationRepository.findByRepresentationUsersById(reservation));
        }
        modelAndView.addObject("representations", representations);
        modelAndView.setViewName("reservations");

        return modelAndView;
    }
}
