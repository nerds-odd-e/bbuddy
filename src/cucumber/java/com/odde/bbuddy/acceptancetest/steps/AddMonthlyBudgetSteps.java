package com.odde.bbuddy.acceptancetest.steps;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AddMonthlyBudgetSteps implements En {

    WebDriver driver;

    {
        When("^add budget for \"([^\"]*)\" with amount (\\d+)$", (String month, Integer budget) -> {
            driver = new FirefoxDriver();
            driver.get("http://localhost:8080/add_budget_for_month");
            WebElement monthTextBox = driver.findElement(By.name("month"));
            monthTextBox.sendKeys(month);
            WebElement budgetTextBox = driver.findElement(By.name("budget"));
            budgetTextBox.sendKeys(String.valueOf(budget));
            budgetTextBox.submit();
        });

        Then("^monthly budget (\\d+) for \"([^\"]*)\" is saved$", (Integer arg1, String arg2) -> {
            driver.close();
        });
    }
}
