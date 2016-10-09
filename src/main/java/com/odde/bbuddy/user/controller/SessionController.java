package com.odde.bbuddy.user.controller;

import com.odde.bbuddy.user.domain.AuthenticationResult;
import com.odde.bbuddy.user.view.SignInView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public ModelAndView signIn(@ModelAttribute AuthenticationResult authenticationResult) {
        signInView.display(authenticationResult);
        return signInView;
    }
}
