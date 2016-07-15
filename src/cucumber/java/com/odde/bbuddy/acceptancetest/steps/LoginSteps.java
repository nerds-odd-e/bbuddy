package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.Application;
import cucumber.api.java8.En;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@IntegrationTest
public class LoginSteps implements En {

    WebDriver driver = new ChromeDriver();

    {

        Given("^there is a user named \"([^\"]*)\" and password is \"([^\"]*)\"$", (String userName, String password) -> {
        });

        When("^login with user name \"([^\"]*)\" and password \"([^\"]*)\"$", (String userName, String password) -> {
            driver.get("http://localhost:8080/login");
            WebElement userNameTextBox = driver.findElement(By.name("username"));
            userNameTextBox.sendKeys(userName);
            WebElement passwordBox = driver.findElement(By.name("password"));
            passwordBox.sendKeys(password);
            userNameTextBox.submit();
        });

        Then("^login successfully$", () -> {
            String bodyText = driver.findElement(By.tagName("body")).getText();
            assertTrue(bodyText.contains("Welcome"));
        });
    }
}
