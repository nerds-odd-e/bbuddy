package com.odde.bbuddy.acceptancetest.steps;

import cucumber.api.java8.En;

public class GetAmountSteps implements En {

    {
        Given("^no budget for any month$", () -> {
        });

        When("^get amount of period from \"([^\"]*)\" to \"([^\"]*)\"$", (String arg1, String arg2) -> {
        });

        Then("^the amount is (\\d+)$", (Integer arg1) -> {
        });
    }
}
