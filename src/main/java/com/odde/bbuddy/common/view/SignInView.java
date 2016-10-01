package com.odde.bbuddy.common.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.common.controller.Urls.SIGNIN;
import static com.odde.bbuddy.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class SignInView extends ModelAndView {

    @Value("${authentication.failed}")
    String failedMessage;

    @Value("${authentication.logout}")
    String logoutMessage;

    public SignInView() {
        setViewName(SIGNIN);
    }

    public ModelAndView display(String error, String logout) {
        if (error != null) setMessageAndType(failedMessage, "danger");

        if (logout != null) setMessageAndType(logoutMessage, "info");

        return this;
    }

    private void setMessageAndType(String message, String type) {
        addObject("message", message);
        addObject("type", type);
    }
}
