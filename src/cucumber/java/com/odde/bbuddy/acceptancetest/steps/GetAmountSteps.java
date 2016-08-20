package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.pages.CommonPage;
import com.odde.bbuddy.acceptancetest.pages.MonthlyBudgetAmountPage;
import com.odde.bbuddy.budget.MonthlyBudget;
import com.odde.bbuddy.budget.MonthlyBudgetRepo;
import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static com.odde.bbuddy.acceptancetest.steps.Formats.MONTH;
import static org.junit.Assert.assertTrue;

public class GetAmountSteps {

    @Autowired
    MonthlyBudgetAmountPage page;

    @Autowired
    CommonPage commonPage;

    @Autowired
    MonthlyBudgetRepo monthlyBudgetRepo;

    @Given("^budget planned for \"([^\"]*)\" is (\\d+)$")
    public void budget_planned_for_is(@Format(MONTH) Date month, int budget) throws Throwable {
        monthlyBudgetRepo.save(new MonthlyBudget(month, budget));
    }

    @When("^get amount of period from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void get_amount_of_period_from_to(String startDate, String endDate) throws Throwable {
        page.open(startDate, endDate);
    }

    @Then("^the amount is (\\d+)$")
    public void the_amount_is(int amount) throws Throwable {
        assertTrue(commonPage.getAllText().contains(String.valueOf(amount)));
    }
}
