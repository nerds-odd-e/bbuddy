package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.data.transaction.DisplayedTransaction;
import com.odde.bbuddy.acceptancetest.data.transaction.EditableTransaction;
import com.odde.bbuddy.acceptancetest.data.transaction.TransactionRepoForTest;
import com.odde.bbuddy.acceptancetest.pages.AddTransactionPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.odde.bbuddy.acceptancetest.data.transaction.DisplayedTransaction.expectedTransactions;
import static com.odde.bbuddy.acceptancetest.steps.AssertionHelper.assertListDeepEquals;

public class TransactionAddSteps {

    @Autowired
    AddTransactionPage addTransactionPage;

    @Autowired
    TransactionRepoForTest transactionRepo;

    @When("^add transactions with the following information$")
    public void add_transactions_with_the_following_information(List<EditableTransaction> transactions) throws Throwable {
        transactions.forEach(transaction -> addTransactionPage.add(transaction));
    }

    @Then("^the following transactions will be created$")
    public void the_following_transactions_will_be_created(List<DisplayedTransaction> displayedTransactions) throws Throwable {
        assertListDeepEquals(expectedTransactions(displayedTransactions), transactionRepo.findAll());
    }

}
