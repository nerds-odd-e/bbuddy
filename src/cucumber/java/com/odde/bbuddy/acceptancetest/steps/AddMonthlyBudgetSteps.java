package com.odde.bbuddy.acceptancetest.steps;

import cucumber.api.java8.En;

public class AddMonthlyBudgetSteps implements En {

    {
        When("^add budget for \"([^\"]*)\" with amount (\\d+)$", (String arg1, Integer arg2) -> {
        });

        Then("^monthly budget (\\d+) for \"([^\"]*)\" is saved$", (Integer arg1, String arg2) -> {
        });
    }
}
