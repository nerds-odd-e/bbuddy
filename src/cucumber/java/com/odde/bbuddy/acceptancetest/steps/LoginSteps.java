package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.user.User;
import com.odde.bbuddy.user.UserRepo;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;


public class LoginSteps {

    @Autowired
    UiDriver driver;

    @Autowired
    UserRepo userRepo;

    @Given("^there is a user named \"([^\"]*)\" and password is \"([^\"]*)\"$")
    public void there_is_a_user_named_and_password_is(String userName, String password) throws Throwable {
        userRepo.save(new User(userName, password));
    }

    @When("^login with user name \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void login_with_user_name_and_password(String userName, String password) throws Throwable {
        driver.getWebDriver().get("http://localhost:8080/login");
        WebElement userNameTextBox = driver.getWebDriver().findElement(By.name("username"));
        userNameTextBox.sendKeys(userName);
        WebElement passwordBox = driver.getWebDriver().findElement(By.name("password"));
        passwordBox.sendKeys(password);
        userNameTextBox.submit();
    }

    @Then("^login successfully$")
    public void login_successfully() throws Throwable {
        String bodyText = driver.getWebDriver().findElement(By.tagName("body")).getText();
        assertTrue(bodyText.contains("Welcome"));
    }
}
