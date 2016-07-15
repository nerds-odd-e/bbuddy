package com.odde.bbuddy.acceptancetest.steps;

import cucumber.api.java8.En;

public class LoginSteps implements En {

    {
        Given("^there is a user named \"([^\"]*)\" and password is \"([^\"]*)\"$", (String arg1, String arg2) -> {
        });

        When("^login with user name \"([^\"]*)\" and password \"([^\"]*)\"$", (String arg1, String arg2) -> {
        });

        Then("^login successfully$", () -> {
        });
    }
}
