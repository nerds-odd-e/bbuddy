package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.pages.AddMonthlyBudgetPage;
import com.odde.bbuddy.budget.MonthlyBudget;
import com.odde.bbuddy.budget.MonthlyBudgetRepo;
import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static com.odde.bbuddy.acceptancetest.steps.Formats.*;
import static org.junit.Assert.assertEquals;

public class AddMonthlyBudgetSteps {

    @Autowired
    AddMonthlyBudgetPage page;

    @Autowired
    MonthlyBudgetRepo monthlyBudgetRepo;

    @When("^add budget for \"([^\"]*)\" with amount (\\d+)$")
    public void add_budget_for_with_amount(String month, int budget) throws Throwable {
        page.addMonthlyBudget(month, budget);
    }

    @Then("^monthly budget (\\d+) for \"([^\"]*)\" is saved$")
    public void monthly_budget_for_is_saved(Integer budget, @Format(MONTH) Date month) throws Throwable {
        assertEquals(1, monthlyBudgetRepo.count());
        monthlyBudgetRepo.findAll().forEach(monthlyBudget -> {
            assertEquals(month, monthlyBudget.getMonth());
            assertEquals(budget, monthlyBudget.getBudget());
        });
    }

    @Given("^budget (\\d+) has been set for month \"([^\"]*)\"$")
    public void budget_has_been_set_for_month(int budget, @Format(MONTH) Date month) throws Throwable {
        monthlyBudgetRepo.save(new MonthlyBudget(month, budget));
    }

    @When("^add budget for \"([^\"]*)\" with a new amount (\\d+)$")
    public void add_budget_for_with_a_new_amount(String month, int budget) throws Throwable {
        add_budget_for_with_amount(month, budget);
    }

    @Then("^the budget for \"([^\"]*)\" is (\\d+)$")
    public void the_budget_for_is(@Format(MONTH) Date month, int budget) throws Throwable {
        budget_has_been_set_for_month(budget, month);
    }
}
