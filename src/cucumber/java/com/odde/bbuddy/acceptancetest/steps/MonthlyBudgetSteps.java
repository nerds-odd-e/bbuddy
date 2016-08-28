package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.data.budget.MonthlyBudgetRepoForTest;
import com.odde.bbuddy.acceptancetest.pages.AddMonthlyBudgetPage;
import com.odde.bbuddy.acceptancetest.pages.CommonPage;
import com.odde.bbuddy.acceptancetest.pages.MonthlyBudgetAmountPage;
import com.odde.bbuddy.budget.domain.MonthlyBudget;
import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;

import static com.odde.bbuddy.acceptancetest.steps.AssertionHelper.*;
import static com.odde.bbuddy.common.Formats.MONTH;
import static org.junit.Assert.assertTrue;

public class MonthlyBudgetSteps {

    @Autowired
    AddMonthlyBudgetPage addMonthlyBudgetPage;

    @Autowired
    MonthlyBudgetAmountPage monthlyBudgetAmountPage;

    @Autowired
    CommonPage commonPage;

    @Autowired
    MonthlyBudgetRepoForTest monthlyBudgetRepo;

    @Given("^budget (\\d+) has been set for month \"([^\"]*)\"$")
    public void budget_has_been_set_for_month(int budget, @Format(MONTH) Date month) throws Throwable {
        monthlyBudgetRepo.save(new MonthlyBudget(month, budget));
    }

    @Given("^budget planned for \"([^\"]*)\" is (\\d+)$")
    public void budget_planned_for_is(@Format(MONTH) Date month, int budget) throws Throwable {
        budget_has_been_set_for_month(budget, month);
    }

    @When("^add budget for \"([^\"]*)\" with amount (\\d+)$")
    public void add_budget_for_with_amount(String month, int budget) throws Throwable {
        addMonthlyBudgetPage.addMonthlyBudget(month, budget);
    }

    @When("^add budget for \"([^\"]*)\" with a new amount (\\d+)$")
    public void add_budget_for_with_a_new_amount(String month, int budget) throws Throwable {
        add_budget_for_with_amount(month, budget);
    }

    @Then("^monthly budget (\\d+) for \"([^\"]*)\" is saved$")
    public void monthly_budget_for_is_saved(Integer budget, @Format(MONTH) Date month) throws Throwable {
        assertListDeepEquals(Arrays.asList(new MonthlyBudget(month, budget)), monthlyBudgetRepo.findAll());
    }

    @Then("^the budget for \"([^\"]*)\" is (\\d+)$")
    public void the_budget_for_is(@Format(MONTH) Date month, int budget) throws Throwable {
        monthly_budget_for_is_saved(budget, month);
    }

    @When("^get amount of period from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void get_amount_of_period_from_to(String startDate, String endDate) throws Throwable {
        monthlyBudgetAmountPage.open(startDate, endDate);
    }

    @Then("^the amount is (\\d+)$")
    public void the_amount_is(int amount) throws Throwable {
        assertTrue(commonPage.getAllText().contains(String.valueOf(amount)));
    }
}
