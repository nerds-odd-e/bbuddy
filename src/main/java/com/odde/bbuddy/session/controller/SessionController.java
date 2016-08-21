package com.odde.bbuddy.session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SessionController {
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public ModelAndView signIn(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout){
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("message", "Invalid username and password!");
            model.addObject("type", "danger");
        }

        if (logout != null) {
            model.addObject("message", "You've been logged out successfully.");
            model.addObject("type", "info");
        }
        model.setViewName("signin");

        return model;
    }
}
