package com.odde.bbuddy.acceptancetest.pages;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
@PropertySource("classpath:ValidationMessages.properties")
public class ErrorMessages {

    @Value("${org.hibernate.validator.constraints.NotEmpty.message}")
    public String notEmpty;

    @Value("${javax.validation.constraints.NotNull.message}")
    public String notNull;

}
