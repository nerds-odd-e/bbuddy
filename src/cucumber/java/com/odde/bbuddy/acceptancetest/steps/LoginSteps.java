package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.Application;
import cucumber.api.java8.En;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@IntegrationTest
public class LoginSteps implements En {

    {
        Given("^there is a user named \"([^\"]*)\" and password is \"([^\"]*)\"$", (String userName, String password) -> {
        });

        When("^login with user name \"([^\"]*)\" and password \"([^\"]*)\"$", (String userName, String password) -> {
            WebDriver driver = new ChromeDriver();
            driver.get("http://localhost:8080/login");
        });

        Then("^login successfully$", () -> {
        });
    }
}
