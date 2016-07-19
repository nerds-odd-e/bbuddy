package com.odde.bbuddy.acceptancetest.steps;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

public class GetAmountSteps implements En {

    WebDriver driver;

    {
        Given("^no budget for any month$", () -> {
        });

        When("^get amount of period from \"([^\"]*)\" to \"([^\"]*)\"$", (String startDate, String endDate) -> {
            driver = new FirefoxDriver();
            driver.get("http://localhost:8080/get_amount?startDate=" + startDate + "&endDate=" + endDate);
        });

        Then("^the amount is (\\d+)$", (Integer amount) -> {
            String bodyText = driver.findElement(By.tagName("body")).getText();
            assertTrue(bodyText.contains(String.valueOf(amount)));
            driver.close();
        });
    }
}
