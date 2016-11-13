package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.data.Messages;
import com.odde.bbuddy.acceptancetest.pages.CommonPage;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class ErrorMessageSteps {

    @Autowired
    CommonPage commonPage;

    @Autowired
    Messages messages;

    @Then("^there is an error message for blank ([^\"]*)$")
    public void there_is_an_error_message_for_blank_input(String field) throws Throwable {
        assertErrorMessageEquals(field, messages.notBlank);
    }

    @Then("^there is an error message for null ([^\"]*)$")
    public void there_is_an_error_message_for_null_input(String field) throws Throwable {
        assertErrorMessageEquals(field, messages.notNull);
    }

    @Then("^there is an error message for invalid date ([^\"]*)$")
    public void there_is_an_error_message_for_invalid_date_input(String field) throws Throwable {
        assertErrorMessageEquals(field, messages.invalidDate);
    }

    @Then("^there is an error message for duplicate ([^\"]*)$")
    public void there_is_an_error_message_for_duplicate(String field) throws Throwable {
        assertErrorMessageEquals(field, messages.duplicateField);
    }

    @Then("^there is an error message for negative ([^\"]*)$")
    public void there_is_an_error_message_for_negative(String field) throws Throwable {
        assertErrorMessageEquals(field, messages.negativeNumber());
    }

    private void assertErrorMessageEquals(String field, String errorMessageTemplate) {
        assertThat(commonPage.getAllText()).containsIgnoringCase(errorMessageWith(field, errorMessageTemplate));
    }

    private String errorMessageWith(String field, String error) {
        return String.format("%s %s", field, error);
    }

}
