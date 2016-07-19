package com.odde.bbuddy.acceptancetest.steps;

import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

public class GetAmountSteps implements En {

    WebDriver driver = new FirefoxDriver();

    {
        Given("^no budget for any month$", () -> {
        });

        When("^get amount of period from \"([^\"]*)\" to \"([^\"]*)\"$", (String startDate, String endDate) -> {
            driver.get("http://localhost:8080/get_amount?startDate=" + startDate + "&endDate=" + endDate);
        });

        Then("^the amount is (\\d+)$", (Integer amount) -> {
            assertEquals("Bbuddy", driver.getTitle());
            driver.close();
        });
    }
}
