package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.budget.MonthlyBudget;
import com.odde.bbuddy.budget.MonthlyBudgetRepo;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class GetAmountSteps {

    @Autowired
    UiDriver driver;

    @Autowired
    MonthlyBudgetRepo monthlyBudgetRepo;

    @Given("^budget planned for \"([^\"]*)\" is (\\d+)$")
    public void budget_planned_for_is(String month, int budget) throws Throwable {
        Date monthDate = null;
        try {
            monthDate = new SimpleDateFormat("yyyy-MM").parse(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        monthlyBudgetRepo.save(new MonthlyBudget(monthDate, budget));
    }

    @When("^get amount of period from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void get_amount_of_period_from_to(String startDate, String endDate) throws Throwable {
        driver.getWebDriver().get("http://localhost:8080/get_amount?startDate=" + startDate + "&endDate=" + endDate);
    }

    @Then("^the amount is (\\d+)$")
    public void the_amount_is(int amount) throws Throwable {
        String bodyText = driver.getWebDriver().findElement(By.tagName("body")).getText();
        assertTrue(bodyText.contains(String.valueOf(amount)));
        monthlyBudgetRepo.deleteAll();
    }
}
