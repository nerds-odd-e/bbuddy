package com.odde.bbuddy.common.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import static com.odde.bbuddy.common.view.Messages.RESULT_MESSAGES;

@Component
@PropertySource(RESULT_MESSAGES)
public class SignIn {
    @Value("${authentication.failed}")
    String failedMessage;

    @Value("${authentication.logout}")
    String logoutMessage;

    public void display(String error, String logout, Model model) {
        if (error != null) setMessageAndType(model, failedMessage, "danger");

        if (logout != null) setMessageAndType(model, logoutMessage, "info");
    }

    private void setMessageAndType(Model model, String failedMessage, String type) {
        model.addAttribute("message", failedMessage);
        model.addAttribute("type", type);
    }
}
