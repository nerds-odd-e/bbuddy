package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transaction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestTransactionController {

    @Test
    public void back_page() {
        TransactionController controller = new TransactionController();

        assertEquals("add_transaction", controller.confirm(new Transaction()));
    }
}
