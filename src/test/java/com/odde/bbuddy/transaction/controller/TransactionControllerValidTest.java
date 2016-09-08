package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static com.odde.bbuddy.Urls.TRANSACTION_ADD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TransactionControllerValidTest {

    Model stubModel = mock(Model.class);
    Transaction transaction = new Transaction();
    BindingResult stubBindingResult = mock(BindingResult.class);
    Transactions mockTransactions = mock(Transactions.class);
    TransactionController controller = new TransactionController(mockTransactions);

    @Before
    public void givenHasFieldErrors() {
        when(stubBindingResult.hasFieldErrors()).thenReturn(true);
    }

    @Test
    public void will_not_add_transaction_when_has_field_error() {
        submitTransactionAdd();

        verify(mockTransactions, never()).add(transaction);
    }

    @Test
    public void will_go_to_add_transaction_page_when_has_field_error() {
        assertThat(submitTransactionAdd()).isEqualTo(TRANSACTION_ADD);
    }

    private String submitTransactionAdd() {
        return controller.submitAddTransaction(transaction, stubBindingResult, stubModel);
    }

}
