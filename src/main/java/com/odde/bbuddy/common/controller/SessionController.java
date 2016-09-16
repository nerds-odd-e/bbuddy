package com.odde.bbuddy.common.controller;

import com.odde.bbuddy.common.view.SignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.odde.bbuddy.common.controller.Urls.SIGNIN;

@Controller
public class SessionController {

    private final SignIn signIn;

    @Autowired
    public SessionController(SignIn signIn) {
        this.signIn = signIn;
    }

    @RequestMapping(value = SIGNIN, method = RequestMethod.GET)
    public String signIn(@RequestParam(value = "error", required = false) String error,
                         @RequestParam(value = "logout", required = false) String logout,
                         Model model){
        signIn.display(error, logout, model);

        return SIGNIN;
    }
}
