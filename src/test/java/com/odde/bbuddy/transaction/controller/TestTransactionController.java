package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestTransactionController {

    Transactions transactions = mock(Transactions.class);
    TransactionController controller = new TransactionController(transactions);

    @Test
    public void back_page() {
        assertEquals("redirect:/add_transaction", controller.addTransaction(new Transaction()));
    }

    @Test
    public void add_transaction() {
        Transaction transaction = new Transaction();

        controller.addTransaction(transaction);

        verify(transactions).add(transaction);
    }
}
