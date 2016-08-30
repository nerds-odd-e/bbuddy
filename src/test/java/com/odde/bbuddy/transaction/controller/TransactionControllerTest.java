package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.common.PostActions;
import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.junit.Test;
import org.springframework.ui.Model;

import static com.odde.bbuddy.common.PostActionsFactory.failed;
import static com.odde.bbuddy.common.PostActionsFactory.success;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TransactionControllerTest {

    Transactions mockTransactions = mock(Transactions.class);
    TransactionController controller = new TransactionController(mockTransactions);
    Model mockModel = mock(Model.class);
    Transaction transaction = new Transaction();

    @Test
    public void back_page() {
        given_add_transaction_will(success());

        assertEquals("add_transaction", controller.submitAddTransaction(transaction, mockModel));
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
