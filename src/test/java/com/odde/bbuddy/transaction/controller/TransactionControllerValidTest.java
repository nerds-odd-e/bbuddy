package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.mockito.Mockito.*;

public class TransactionControllerValidTest {

    @Test
    public void will_not_add_transaction_when_has_field_error() {
        Model stubModel = mock(Model.class);
        Transaction transaction = new Transaction();
        BindingResult stubBindingResult = mock(BindingResult.class);
        Transactions mockTransactions = mock(Transactions.class);
        TransactionController controller = new TransactionController(mockTransactions);
        when(stubBindingResult.hasFieldErrors()).thenReturn(true);

        controller.submitAddTransaction(transaction, stubBindingResult, stubModel);

        verify(mockTransactions, never()).add(transaction);
    }
}
