package com.odde.bbuddy.common.view;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.FieldError;

public class ErrorMessage {
    public ErrorMessage(ModelMap model, FieldError fieldError, MessageSource messageSource) {
        model.addAttribute("error." + fieldError.getField(), messageSource.getMessage(fieldError, null));
    }
}
