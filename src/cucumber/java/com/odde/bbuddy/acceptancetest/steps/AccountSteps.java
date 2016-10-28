package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.data.Messages;
import com.odde.bbuddy.acceptancetest.data.account.AccountRepoForTest;
import com.odde.bbuddy.acceptancetest.data.account.EditableAccount;
import com.odde.bbuddy.acceptancetest.pages.AddAccountPage;
import com.odde.bbuddy.acceptancetest.pages.CommonPage;
import com.odde.bbuddy.account.domain.Account;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.odde.bbuddy.acceptancetest.steps.AssertionHelper.assertListDeepEquals;

public class AccountSteps {

    @Autowired
    AddAccountPage addAccountPage;

    @Autowired
    CommonPage commonPage;

    @Autowired
    AccountRepoForTest accountRepoForTest;

    @Autowired
    Messages messages;

    @When("^add account with the following information$")
    public void add_account_with_the_following_information(List<EditableAccount> accounts) throws Throwable {
        accounts.forEach(addAccountPage::add);
    }

    @Then("^the following account will be created$")
    public void the_following_account_will_be_created(List<Account> expected) throws Throwable {
        assertListDeepEquals(expected, accountRepoForTest.findAll());
    }

    @Given("^existing an account with name \"([^\"]*)\"$")
    public void existing_an_account_with_name(String name) throws Throwable {
        Account account = new Account();
        account.setName(name);
        accountRepoForTest.save(account);
    }

    @When("^add a new account with name \"([^\"]*)\"$")
    public void add_a_new_account_with_name(String name) throws Throwable {
        EditableAccount account = new EditableAccount();
        account.setName(name);
        account.setBalanceBroughtForward("0");
        addAccountPage.add(account);
    }

    @Then("^there is an error message for duplicate name$")
    public void there_is_an_error_message_for_duplicate_name() throws Throwable {
//        assertThat(commonPage.getAllText()).contains(messages.duplicateAccountName);
    }

}
