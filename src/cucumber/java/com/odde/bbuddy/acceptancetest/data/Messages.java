package com.odde.bbuddy.acceptancetest.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
@PropertySources({
        @PropertySource("classpath:ValidationMessages.properties"),
        @PropertySource("classpath:BindingErrorMessages.properties"),
        @PropertySource("classpath:labelText.properties")
})
public class Messages {

    @Value("${org.hibernate.validator.constraints.NotBlank.message}")
    public String notBlank;

    @Value("${javax.validation.constraints.NotNull.message}")
    public String notNull;

    @Value("${typeMismatch.java.util.Date}")
    public String invalidDate;

    @Value("${typeMismatch.java.lang.Integer}")
    public String invalidNumber;

    @Value("${home.label.welcome}")
    public String welcome;

    @Value("${authentication.failed}")
    public String loginFailed;

    @Value("${authentication.logout}")
    public String logout;
}
