package com.odde.bbuddy.common.view;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class SignInView {
    @Value("${authentication.failed}")
    String failedMessage;

    @Value("${authentication.logout}")
    String logoutMessage;

    private final HttpServletRequest request;

    @Autowired
    public SignInView(HttpServletRequest request) {
        this.request = request;
    }

    public void display(String error, String logout) {
        if (error != null) setMessageAndType(failedMessage, "danger");

        if (logout != null) setMessageAndType(logoutMessage, "info");
    }

    private void setMessageAndType(String message, String type) {
        request.setAttribute("message", message);
        request.setAttribute("type", type);
    }
}
