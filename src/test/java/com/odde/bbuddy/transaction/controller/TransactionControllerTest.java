package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.junit.Test;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TransactionControllerTest {

    Transactions transactions = mock(Transactions.class);
    TransactionController controller = new TransactionController(transactions);
    Model mockModel = mock(Model.class);
    Transaction transaction = new Transaction();

    @Test
    public void back_page() {
        assertEquals("add_transaction", controller.addTransaction(transaction, mockModel));
    }

    @Test
    public void add_transaction() {
        controller.addTransaction(transaction, mockModel);

        verify(transactions).add(eq(transaction), any(Runnable.class));
    }

    @Test
    public void add_transaction_successfully() {
        given_add_transaction_will_success();

        controller.addTransaction(transaction, mockModel);

        verify(mockModel).addAttribute("message", "Successfully add transaction");
    }

    private void given_add_transaction_will_success() {
        doAnswer(invocation -> {
            Runnable afterSuccess = (Runnable) invocation.getArguments()[1];
            afterSuccess.run();
            return null;
        }).when(transactions).add(any(Transaction.class), any(Runnable.class));
    }
}
