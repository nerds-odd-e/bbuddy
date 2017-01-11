package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.data.account.AccountRepoForTest;
import com.odde.bbuddy.acceptancetest.data.account.DisplayedAccount;
import com.odde.bbuddy.acceptancetest.pages.CommonPage;
import com.odde.bbuddy.acceptancetest.pages.ShowAllAccountsPage;
import com.odde.bbuddy.account.repo.Account;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountListSteps {

    @Autowired
    AccountRepoForTest accountRepo;

    @Autowired
    ShowAllAccountsPage showAllAccountsPage;

    @Autowired
    CommonPage commonPage;

    @Given("^exists the following accounts$")
    public void exists_the_following_accounts(List<Account> accounts) throws Throwable {
        accounts.forEach(accountRepo::save);
    }

    @When("^show all accounts$")
    public void show_all_accounts() throws Throwable {
        showAllAccountsPage.show();
    }

    @Then("^you will see all accounts as below$")
    public void you_will_see_all_accounts_as_below(List<DisplayedAccount> expected) throws Throwable {
        expected.forEach(displayedAccount -> displayedAccount.assertAllFieldsDisplayedIn(commonPage.getAllText()));
    }
}
