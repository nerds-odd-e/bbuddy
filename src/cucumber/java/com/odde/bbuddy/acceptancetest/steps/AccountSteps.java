package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.data.account.EditableAccount;
import com.odde.bbuddy.acceptancetest.pages.AddAccountPage;
import com.odde.bbuddy.account.domain.Account;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountSteps {

    @Autowired
    AddAccountPage addAccountPage;

    @When("^add account with the following information$")
    public void add_account_with_the_following_information(List<EditableAccount> accounts) throws Throwable {
        accounts.forEach(addAccountPage::add);
    }

    @Then("^the following account will be created$")
    public void the_following_account_will_be_created(List<Account> expected) throws Throwable {
    }

}
