package com.odde.bbuddy.common.view;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
public class ErrorMessage {

    private final MessageSource messageSource;
    private final HttpServletRequest request;

    @Autowired
    public ErrorMessage(MessageSource messageSource, HttpServletRequest request) {
        this.messageSource = messageSource;
        this.request = request;
    }

    public void display(FieldError fieldError) {
        request.setAttribute("error." + fieldError.getField(), messageSource.getMessage(fieldError, null));
    }
}
