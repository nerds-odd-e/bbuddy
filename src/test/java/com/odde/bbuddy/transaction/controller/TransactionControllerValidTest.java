package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static com.odde.bbuddy.common.controller.Urls.TRANSACTION_ADD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TransactionControllerValidTest {

    Model mockModel = mock(Model.class);
    Transaction invalidTransaction = new Transaction();
    BindingResult stubBindingResult = mock(BindingResult.class);
    Transactions mockTransactions = mock(Transactions.class);
    TransactionController controller = new TransactionController(mockTransactions);

    @Before
    public void givenHasFieldErrors() {
        when(stubBindingResult.hasFieldErrors()).thenReturn(true);
    }

    @Test
    public void will_not_add_transaction_when_has_field_error() {
        submitAddTransaction();

        verify(mockTransactions, never()).add(invalidTransaction);
    }

    @Test
    public void will_go_to_add_transaction_page_when_has_field_error() {
        assertThat(submitAddTransaction()).isEqualTo(TRANSACTION_ADD);
    }

    @Test
    public void will_show_all_transaction_types_when_has_field_error() {
        submitAddTransaction();

        verify(mockModel).addAttribute("types", Transaction.Type.values());
    }

    private String submitAddTransaction() {
        return controller.submitAddTransaction(invalidTransaction, stubBindingResult, mockModel);
    }

}
