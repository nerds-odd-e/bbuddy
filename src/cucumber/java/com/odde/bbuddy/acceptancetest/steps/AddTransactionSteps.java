package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.pages.AddTransactionPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AddTransactionSteps {

    @Autowired
    AddTransactionPage addTransactionPage;

    @When("^add a new transaction with the following information$")
    public void add_a_new_transaction_with_the_following_information(List<Transaction> transactions) throws Throwable {
        addTransactionPage.add(transactions.get(0));
    }

    @Then("^a new transaction will be created$")
    public void a_new_transaction_will_be_created(List<Transaction> transactions) throws Throwable {
    }
}
