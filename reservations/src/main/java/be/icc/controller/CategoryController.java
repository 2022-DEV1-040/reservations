package be.icc.controller;

import be.icc.entity.*;
import be.icc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {


    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public ModelAndView shows(ModelAndView modelAndView) {

        modelAndView.addObject("categories", categoryRepository.findAll());
        modelAndView.setViewName("categories");

        return modelAndView;
    }

    @RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @RequestParam(value = "idCategory", required = true) Long idCategory) {
        categoryRepository.delete(idCategory);
        modelAndView.addObject("categories", categoryRepository.findAll());
        modelAndView.setViewName("categories");
        return modelAndView;
    }

    @RequestMapping(value = "/categoryInfo", method = RequestMethod.GET)
    public String categoryInfo(Model model, @RequestParam(value = "idCategory", required = true) Long idCategory) {
        model.addAttribute("category",categoryRepository.findOne(idCategory));

        return "categoryInfo";
    }

    // Process form input data
    @RequestMapping(value = "/modifyCategory", method = RequestMethod.POST)
    public ModelAndView modifyCategory(ModelAndView modelAndView, CategoryEntity categoryEntity, BindingResult bindingResult, @RequestParam(value = "idCategory", required = true) Long idCategory) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("categoryInfo");
        } else if(categoryEntity.getNom().length()>10) {
            modelAndView.addObject("category", categoryEntity);
            modelAndView.addObject("errorMessage", "Le nom ne peut pas faire plus de 10 caracteres");
            modelAndView.setViewName("categoryInfo");
        } else {
            CategoryEntity category = categoryRepository.findOne(idCategory);
            category.setNom(categoryEntity.getNom());
            categoryRepository.save(category);

            modelAndView.addObject("category", category);
            modelAndView.addObject("confirmationMessage", "La categories a ete mise à jour");
            modelAndView.setViewName("categoryInfo");
        }

        return modelAndView;
    }

}
