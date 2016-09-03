package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transactions;
import org.junit.Test;

import static com.odde.bbuddy.Urls.TRANSACTION_LIST;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class TransactionListControllerTest {

    Transactions mockTransactions = mock(Transactions.class);
    TransactionController controller = new TransactionController(mockTransactions);

    @Test
    public void go_to_transaction_list_page() {
        assertEquals(TRANSACTION_LIST, controller.showTransactions());
    }
}
