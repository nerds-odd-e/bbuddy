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

    public PastValidatorForLocalDate() {
        //this constructor must be there so that Spring DI works for this validator
    }

    public PastValidatorForLocalDate(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void initialize(Past constraintAnnotation) {
        //no implementation needed
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null)
            return true;

        return value.isBefore(now(clock)) || value.isEqual(now(clock));
    }
}
