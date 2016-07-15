package com.odde.bbuddy.acceptancetest.steps;

import cucumber.api.PendingException;
import cucumber.api.java8.En;

public class LoginSteps implements En {

    {
        Given("^there is a user named \"([^\"]*)\" and password is \"([^\"]*)\"$", (String arg1, String arg2) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });

        When("^login with user name \"([^\"]*)\" and password \"([^\"]*)\"$", (String arg1, String arg2) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });

        Then("^login successfully$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
    }
}
