package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.data.Messages;
import com.odde.bbuddy.acceptancetest.pages.CommonPage;
import com.odde.bbuddy.acceptancetest.pages.HomePage;
import com.odde.bbuddy.acceptancetest.pages.LabelTexts;
import com.odde.bbuddy.acceptancetest.pages.SignInPage;
import com.odde.bbuddy.user.repo.User;
import com.odde.bbuddy.user.repo.UserRepo;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class SignInSteps {

    @Autowired
    UserRepo userRepo;

    @Autowired
    SignInPage signInPage;

    @Autowired
    CommonPage commonPage;

    @Autowired
    HomePage homePage;

    @Autowired
    Messages messages;

    @Autowired
    LabelTexts labelTexts;

    @Given("^there is a user named \"([^\"]*)\" and password is \"([^\"]*)\"$")
    public void there_is_a_user_named_and_password_is(String userName, String password) throws Throwable {
        userRepo.save(new User(userName, password));
    }

    @When("^login with user name \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void login_with_user_name_and_password(String userName, String password) throws Throwable {
        signInPage.signIn(userName, password);
    }

    @Then("^login successfully$")
    public void login_successfully() throws Throwable {
        assertThat(commonPage.getAllText()).contains(labelTexts.welcome);
    }

    @Then("^login failed with some message$")
    public void login_failed_with_some_message() throws Throwable {
        assertThat(commonPage.getAllText()).contains(messages.loginFailed);
    }

    @When("^logout$")
    public void logout() throws Throwable {
        homePage.signout();
    }

    @Then("^logout with some message$")
    public void logout_with_some_message() throws Throwable {
        assertThat(commonPage.getAllText()).contains(messages.logout);
    }
}
