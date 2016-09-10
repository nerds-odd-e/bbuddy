package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.data.transaction.EditableTransaction;
import com.odde.bbuddy.acceptancetest.data.transaction.TransactionRepoForTest;
import com.odde.bbuddy.acceptancetest.pages.AddTransactionPage;
import com.odde.bbuddy.acceptancetest.pages.CommonPage;
import com.odde.bbuddy.acceptancetest.pages.ShowAllTransactionsPage;
import com.odde.bbuddy.acceptancetest.pages.ErrorMessages;
import com.odde.bbuddy.transaction.domain.Transaction;
import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.odde.bbuddy.acceptancetest.steps.AssertionHelper.assertListDeepEquals;
import static com.odde.bbuddy.common.Formats.DAY;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionSteps {

    @Autowired
    AddTransactionPage addTransactionPage;

    @Autowired
    ShowAllTransactionsPage showAllTransactionsPage;

    @Autowired
    TransactionRepoForTest transactionRepo;

    @Autowired
    CommonPage commonPage;

    @Autowired
    ErrorMessages errorMessages;

    @When("^add transactions with the following information$")
    public void add_transactions_with_the_following_information(List<EditableTransaction> transactions) throws Throwable {
        transactions.forEach(transaction -> addTransactionPage.add(transaction));
    }

    @Then("^the following transactions will be created$")
    public void the_following_transactions_will_be_created(@Format(DAY) List<Transaction> expected) throws Throwable {
        assertListDeepEquals(expected, transactionRepo.findAll(), "date");
    }

    @Given("^exists the following transactions$")
    public void exists_the_following_transactions(@Format(DAY) List<Transaction> transactions) throws Throwable {
        transactions.forEach(transaction -> transactionRepo.save(transaction));
    }

    @When("^show all transactions$")
    public void show_all_transactions() throws Throwable {
        showAllTransactionsPage.show();
    }

    @Then("^you will see all transactions as below$")
    public void you_will_see_all_transactions_as_belw(List<EditableTransaction> transactions) throws Throwable {
    }

    @When("^add transaction with no data$")
    public void add_transaction_with_no_data() throws Throwable {
        addTransactionPage.add(noDataInputTransaction());
    }

    private EditableTransaction noDataInputTransaction() {
        return new EditableTransaction().populateAllEmptyAndDefaultData();
    }

    @Then("^there is an error message for empty ([^\"]*)$")
    public void there_is_an_error_message_for_empty_input(String field) throws Throwable {
        assertThat(commonPage.getAllText()).containsIgnoringCase(errorMessageWith(field, errorMessages.notEmpty));
    }

    @Then("^there is an error message for null ([^\"]*)$")
    public void there_is_an_error_message_for_null_input(String field) throws Throwable {
        assertThat(commonPage.getAllText()).containsIgnoringCase(errorMessageWith(field, errorMessages.notNull));
    }

    private String errorMessageWith(String field, String error) {
        return String.format("%s %s", field, error);
    }

}
