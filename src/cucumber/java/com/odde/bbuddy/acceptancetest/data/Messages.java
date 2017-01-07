package com.odde.bbuddy.acceptancetest.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.view.MessagePropertyNamesWithSyntax.AUTHENTICATION_FAILED;
import static com.odde.bbuddy.common.view.MessagePropertyNamesWithSyntax.AUTHENTICATION_LOGOUT;
import static com.odde.bbuddy.common.view.MessageSources.BINDING_ERROR_MESSAGE_FULL_NAME;
import static com.odde.bbuddy.common.view.MessageSources.VALIDATION_MESSAGE_FULL_NAME;

@Component
@Scope("cucumber-glue")
@PropertySources({
        @PropertySource(VALIDATION_MESSAGE_FULL_NAME),
        @PropertySource(BINDING_ERROR_MESSAGE_FULL_NAME),
})
public class Messages {

    @Value("${org.hibernate.validator.constraints.NotBlank.message}")
    public String notBlank;

    @Value("${javax.validation.constraints.NotNull.message}")
    public String notNull;

    @Value("${typeMismatch.java.time.LocalDate}")
    public String invalidDate;

    @Value("${typeMismatch.java.lang.Integer}")
    public String invalidNumber;

    @Value(AUTHENTICATION_FAILED)
    public String loginFailed;

    @Value(AUTHENTICATION_LOGOUT)
    public String logout;

    @Value("${com.odde.bbuddy.Unique.message}")
    public String duplicateField;

    @Value("${javax.validation.constraints.Min.message}")
    private String minNumberMessage;

    public String negativeNumber() {
        return minNumberMessage.replace("{value}", "0");
    }
}
