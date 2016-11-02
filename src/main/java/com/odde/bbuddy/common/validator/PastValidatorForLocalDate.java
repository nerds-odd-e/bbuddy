package com.odde.bbuddy.common.validator;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Clock;
import java.time.LocalDate;

import static java.time.LocalDate.now;

public class PastValidatorForLocalDate implements ConstraintValidator<Past, LocalDate> {

    @Autowired
    private Clock clock;

    public PastValidatorForLocalDate() { }

    public PastValidatorForLocalDate(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void initialize(Past constraintAnnotation) {

    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null)
            return true;

        return value.isBefore(now(clock)) || value.isEqual(now(clock));
    }
}
