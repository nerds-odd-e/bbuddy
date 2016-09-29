package com.odde.bbuddy.common.controller;

import com.odde.bbuddy.common.view.SignInView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.common.controller.Urls.SIGNIN;

@Controller
public class SessionController {

    private final SignInView signInView;

    @Autowired
    public SessionController(SignInView signInView) {
        this.signInView = signInView;
    }

    @GetMapping(SIGNIN)
    public ModelAndView signIn(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout){
        return signInView.display(error, logout);
    }
}
