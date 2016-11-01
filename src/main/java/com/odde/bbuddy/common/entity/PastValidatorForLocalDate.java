package com.odde.bbuddy.common.entity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class PastValidatorForLocalDate implements ConstraintValidator<Past, LocalDate> {
    @Override
    public void initialize(Past constraintAnnotation) {

    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return true;
    }
}
