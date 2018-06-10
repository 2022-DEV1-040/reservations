package be.icc.controller;

import be.icc.entity.ShowsEntity;
import be.icc.repository.ShowRepository;
import be.icc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ShowController {

    @Autowired
    private ShowRepository showRepository;

    @GetMapping("/shows")
    public ModelAndView shows(ModelAndView modelAndView){

        List<ShowsEntity> shows = (List<ShowsEntity>) showRepository.findAll();
        modelAndView.addObject("shows",shows);
        modelAndView.setViewName("shows");

        return modelAndView;
    }

}
