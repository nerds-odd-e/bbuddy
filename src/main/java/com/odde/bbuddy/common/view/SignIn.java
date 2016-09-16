package com.odde.bbuddy.common.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
@PropertySource("classpath:resultMessages.properties")
public class SignIn {
    @Value("${authentication.failed}")
    public
    String failedMessage;

    @Value("${authentication.logout}")
    public
    String logoutMessage;

    public ModelAndView getModel(String error, String logout) {
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
