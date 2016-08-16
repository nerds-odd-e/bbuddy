package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.budget.MonthlyBudget;
import com.odde.bbuddy.budget.MonthlyBudgetRepo;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AddMonthlyBudgetSteps {

    @Autowired
    UiDriver driver;

    @Autowired
    MonthlyBudgetRepo monthlyBudgetRepo;

    @When("^add budget for \"([^\"]*)\" with amount (\\d+)$")
    public void add_budget_for_with_amount(String month, int budget) throws Throwable {
        driver.getWebDriver().get("http://localhost:8080/add_budget_for_month");
        WebElement monthTextBox = driver.getWebDriver().findElement(By.name("month"));
        monthTextBox.sendKeys(month);
        WebElement budgetTextBox = driver.getWebDriver().findElement(By.name("budget"));
        budgetTextBox.sendKeys(String.valueOf((Integer) budget));
        budgetTextBox.submit();
    }

    @Then("^monthly budget (\\d+) for \"([^\"]*)\" is saved$")
    public void monthly_budget_for_is_saved(int budget, String month) throws Throwable {
        assertEquals(1, monthlyBudgetRepo.count());
        monthlyBudgetRepo.findAll().forEach(monthlyBudget -> {
            Date monthDate = null;
            try {
                monthDate = new SimpleDateFormat("yyyy-MM").parse(month);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assertEquals(monthDate, monthlyBudget.getMonth());
            assertEquals((Integer) budget, monthlyBudget.getBudget());
        });
        monthlyBudgetRepo.deleteAll();
    }

    @Given("^budget (\\d+) has been set for month \"([^\"]*)\"$")
    public void budget_has_been_set_for_month(int budget, String month) throws Throwable {
        Date monthDate = null;
        try {
            monthDate = new SimpleDateFormat("yyyy-MM").parse(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        monthlyBudgetRepo.save(new MonthlyBudget(monthDate, budget));
    }

    @When("^add budget for \"([^\"]*)\" with a new amount (\\d+)$")
    public void add_budget_for_with_a_new_amount(String month, int budget) throws Throwable {
        add_budget_for_with_amount(month, budget);
    }

    @Then("^the budget for \"([^\"]*)\" is (\\d+)$")
    public void the_budget_for_is(String month, int budget) throws Throwable {
        budget_has_been_set_for_month(budget, month);
    }
}
