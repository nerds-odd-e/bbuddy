package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.common.PostActions;
import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.junit.Test;
import org.springframework.ui.Model;

import static com.odde.bbuddy.Urls.TRANSACTION_ADD;
import static com.odde.bbuddy.common.PostActionsFactory.failed;
import static com.odde.bbuddy.common.PostActionsFactory.success;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TransactionAddControllerTest {

    Transactions mockTransactions = mock(Transactions.class);
    TransactionController controller = new TransactionController(mockTransactions);
    Model mockModel = mock(Model.class);
    Transaction transaction = new Transaction();

    @Test
    public void go_to_transaction_add_page() {
        assertThat(controller.addTransaction(mockModel)).isEqualTo(TRANSACTION_ADD);
    }

    @Test
    public void show_all_transaction_types() {
        controller.addTransaction(mockModel);

        verify(mockModel).addAttribute("types", Transaction.Type.values());
    }

    @Test
    public void back_page_after_submit() {
        given_add_transaction_will(success());

        assertThat(controller.submitAddTransaction(transaction, mockModel)).isEqualTo(TRANSACTION_ADD);
    }

    @Test
    public void show_all_transaction_types_after_submit() {
        given_add_transaction_will(success());

        controller.submitAddTransaction(transaction, mockModel);

        verify(mockModel).addAttribute("types", Transaction.Type.values());
    }

    @Test
    public void add_transaction() {
        given_add_transaction_will(success());

        controller.submitAddTransaction(transaction, mockModel);

        verify(mockTransactions).add(transaction);
    }

    @Test
    public void add_transaction_successfully() {
        given_add_transaction_will(success());

        controller.submitAddTransaction(transaction, mockModel);

        verify(mockModel).addAttribute("message", "Successfully add transaction");
    }

    @Test
    public void add_transaction_failed() {
        given_add_transaction_will(failed());

        controller.submitAddTransaction(transaction, mockModel);

        verify(mockModel).addAttribute("message", "Add transaction failed");
    }

    private void given_add_transaction_will(PostActions postActions) {
        when(mockTransactions.add(any(Transaction.class))).thenReturn(postActions);
    }
}
