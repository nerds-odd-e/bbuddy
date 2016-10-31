package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.data.transformer.MonthToLocalDateTransformer;
import com.odde.bbuddy.acceptancetest.data.budget.MonthlyBudgetRepoForTest;
import com.odde.bbuddy.acceptancetest.pages.AddMonthlyBudgetPage;
import com.odde.bbuddy.acceptancetest.pages.CommonPage;
import com.odde.bbuddy.acceptancetest.pages.MonthlyBudgetAmountPage;
import com.odde.bbuddy.budget.domain.MonthlyBudget;
import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static com.odde.bbuddy.acceptancetest.steps.AssertionHelper.assertListDeepEquals;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

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
    public void budget_has_been_set_for_month(int budget, @Transform(MonthToLocalDateTransformer.class) LocalDate month) throws Throwable {
        monthlyBudgetRepo.save(new MonthlyBudget(month, budget));
    }

    @Given("^budget planned for \"([^\"]*)\" is (\\d+)$")
    public void budget_planned_for_is(@Transform(MonthToLocalDateTransformer.class) LocalDate month, int budget) throws Throwable {
        budget_has_been_set_for_month(budget, month);
    }

    @When("^add budget for \"([^\"]*)\" with amount (\\d+)$")
    public void add_budget_for_with_amount(String month, int budget) throws Throwable {
        addMonthlyBudgetPage.addMonthlyBudget(month + "-01", String.valueOf(budget));
    }

    @When("^add budget for \"([^\"]*)\" with a new amount (\\d+)$")
    public void add_budget_for_with_a_new_amount(String month, int budget) throws Throwable {
        add_budget_for_with_amount(month, budget);
    }

    @Then("^monthly budget (\\d+) for \"([^\"]*)\" is saved$")
    public void monthly_budget_for_is_saved(Integer budget, @Transform(MonthToLocalDateTransformer.class) LocalDate month) throws Throwable {
        assertListDeepEquals(asList(new MonthlyBudget(month, budget)), monthlyBudgetRepo.findAll());
    }

    @Then("^the budget for \"([^\"]*)\" is (\\d+)$")
    public void the_budget_for_is(@Transform(MonthToLocalDateTransformer.class) LocalDate month, int budget) throws Throwable {
        monthly_budget_for_is_saved(budget, month);
    }

    @When("^get amount of period from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void get_amount_of_period_from_to(String startDate, String endDate) throws Throwable {
        monthlyBudgetAmountPage.open(startDate, endDate);
    }

    @Then("^the amount is (\\d+)$")
    public void the_amount_is(int amount) throws Throwable {
        assertThat(commonPage.getAllText()).contains(String.valueOf(amount));
    }

    @When("^add monthly budget with no month and invalid budget$")
    public void add_monthly_budget_with_no_month_and_invalid_budget() throws Throwable {
        addMonthlyBudgetPage.addMonthlyBudget(null, "invalid budget");
    }

}
