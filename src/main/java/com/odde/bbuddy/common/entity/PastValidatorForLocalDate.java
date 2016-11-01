package com.odde.bbuddy.common.entity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Clock;
import java.time.LocalDate;

import static java.time.LocalDate.now;

public class PastValidatorForLocalDate implements ConstraintValidator<Past, LocalDate> {
    private final Clock clock;

    public PastValidatorForLocalDate() {
        this(Clock.systemDefaultZone());
    }

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
