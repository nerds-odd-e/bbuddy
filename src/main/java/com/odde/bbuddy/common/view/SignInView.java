package com.odde.bbuddy.common.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;

@Component
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class SignInView {
    private final Model model;
    @Value("${authentication.failed}")
    String failedMessage;

    @Value("${authentication.logout}")
    String logoutMessage;

    @Autowired
    public SignInView(Model model) {
        this.model = model;
    }

    public void display(String error, String logout) {
        if (error != null) setMessageAndType(failedMessage, "danger");

        if (logout != null) setMessageAndType(logoutMessage, "info");
    }

    private void setMessageAndType(String message, String type) {
        model.addAttribute("message", message);
        model.addAttribute("type", type);
    }
}
