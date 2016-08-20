package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.pages.CommonPage;
import com.odde.bbuddy.acceptancetest.pages.LoginPage;
import com.odde.bbuddy.user.User;
import com.odde.bbuddy.user.UserRepo;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;


public class LoginSteps {

    @Autowired
    UserRepo userRepo;

    @Autowired
    LoginPage page;

    @Autowired
    CommonPage commonPage;

    @Given("^there is a user named \"([^\"]*)\" and password is \"([^\"]*)\"$")
    public void there_is_a_user_named_and_password_is(String userName, String password) throws Throwable {
        userRepo.save(new User(userName, password));
    }

    @When("^login with user name \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void login_with_user_name_and_password(String userName, String password) throws Throwable {
        page.login(userName, password);
    }

    @Then("^login successfully$")
    public void login_successfully() throws Throwable {
        assertTrue(commonPage.getAllText().contains("Welcome"));
    }
}
