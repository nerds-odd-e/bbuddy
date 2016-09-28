package com.odde.bbuddy.common.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class ErrorMessage {

    private final MessageSource messageSource;
    private final Model model;

    @Autowired
    public ErrorMessage(MessageSource messageSource, Model model) {
        this.messageSource = messageSource;
        this.model = model;
    }

    public void display(FieldError fieldError) {
        model.addAttribute("error." + fieldError.getField(), messageSource.getMessage(fieldError, null));
    }
}
