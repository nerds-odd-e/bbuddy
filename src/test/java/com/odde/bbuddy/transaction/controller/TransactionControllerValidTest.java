package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import static com.odde.bbuddy.Urls.TRANSACTION_ADD;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TransactionControllerValidTest {

    Model mockModel = mock(Model.class);
    Transaction transaction = new Transaction();
    BindingResult stubBindingResult = mock(BindingResult.class);
    Transactions mockTransactions = mock(Transactions.class);
    TransactionController controller = new TransactionController(mockTransactions);

    @Before
    public void givenHasFieldErrors() {
        when(stubBindingResult.hasFieldErrors()).thenReturn(true);
        givenFieldErrors(new FieldError("notUsedObjectName", "field", "error message"));
    }

    private void givenFieldErrors(FieldError... errors) {
        when(stubBindingResult.getFieldErrors()).thenReturn(asList(errors));
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

    @Test
    public void will_show_all_transaction_types_when_has_field_error() {
        submitTransactionAdd();

        verify(mockModel).addAttribute("types", Transaction.Type.values());
    }

    @Test
    public void will_show_error_message_when_has_one_field_error() {
        givenFieldErrors(new FieldError("notUsedObjectName", "field", "error message"));

        submitTransactionAdd();

        verify(mockModel).addAttribute("error.field", "error message");
    }

    @Test
    public void will_show_error_message_when_has_two_field_errors() {
        givenFieldErrors(
                new FieldError("notUsedObjectName", "field", "error message"),
                new FieldError("notUsedObjectName1", "field1", "another error message"));

        submitTransactionAdd();

        verify(mockModel).addAttribute("error.field", "error message");
        verify(mockModel).addAttribute("error.field1", "another error message");
    }

    private String submitTransactionAdd() {
        return controller.submitAddTransaction(transaction, stubBindingResult, mockModel);
    }

}
