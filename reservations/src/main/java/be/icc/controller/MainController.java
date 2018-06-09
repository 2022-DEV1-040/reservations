package be.icc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MainController {

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        return "welcome";
    }

    // Login form
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // Register form
    @RequestMapping("/register")
    public String register() {
        return "register";
    }
}
