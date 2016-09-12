package com.odde.bbuddy.session.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@PropertySource("classpath:resultMessages.properties")
public class SessionController {

    @Value("${authentication.failed}")
    String failedMessage;

    @Value("${authentication.logout}")
    String logoutMessage;

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public ModelAndView signIn(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout){
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("message", failedMessage);
            model.addObject("type", "danger");
        }

        if (logout != null) {
            model.addObject("message", logoutMessage);
            model.addObject("type", "info");
        }
        model.setViewName("signin");

        return model;
    }
}
