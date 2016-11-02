package com.odde.bbuddy.common.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @Autowired
    ApplicationContext applicationContext;

    private FieldCheck fieldCheck;

    @Override
    public void initialize(Unique unique) {
        this.fieldCheck = applicationContext.getBean(unique.fieldCheck());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return fieldCheck.isValueUnique(value);
    }
}
