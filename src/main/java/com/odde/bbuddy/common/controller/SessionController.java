package com.odde.bbuddy.common.controller;

import com.odde.bbuddy.common.view.SignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SessionController {

    @Autowired
    SignIn signIn;

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public ModelAndView signIn(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout){
        return signIn.getModel(error, logout);
    }
}
