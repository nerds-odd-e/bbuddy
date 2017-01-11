package com.odde.bbuddy.transaction.controller;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.common.callback.PostActions;
import com.odde.bbuddy.common.view.View;
import com.odde.bbuddy.transaction.repo.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import com.odde.bbuddy.transaction.view.PresentableAddTransaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.common.callback.PostActionsFactory.failed;
import static com.odde.bbuddy.common.callback.PostActionsFactory.success;
import static com.odde.bbuddy.transaction.builder.TransactionBuilder.defaultTransaction;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class TransactionAddControllerTest {

    Transactions mockTransactions = mock(Transactions.class);
    View mockView = mock(View.class);
    TransactionAddController controller = new TransactionAddController(mockTransactions, new PresentableAddTransaction(), mockView);
    Transaction transaction = defaultTransaction().build();
    BindingResult stubBindingResult = mock(BindingResult.class);

    @Before
    public void given_has_no_field_error() {
        given_has_field_error(false);
    }

    private void given_has_field_error(boolean value) {
        when(stubBindingResult.hasFieldErrors()).thenReturn(value);
    }

    private ModelAndView submitTransactionAdd(Transaction transaction) {
        return controller.submitAddTransaction(transaction, stubBindingResult);
    }

    public class Add {

        @Test
        public void should_go_to_view() {
            assertThat(controller.addTransaction()).isInstanceOf(PresentableAddTransaction.class);
        }

    }

    public class SubmitAdd {

        @Before
        public void given_add_transaction_will_success() {
            given_add_transaction_will(success());
        }

        @Before
        public void given_messages() {
            controller.successMessage = "a success message";
            controller.failedMessage = "a failed message";
        }

        @Test
        public void should_go_to_view() {
            assertThat(submitTransactionAdd(transaction)).isInstanceOf(PresentableAddTransaction.class);
        }

        @Test
        public void should_add_transaction() {
            submitTransactionAdd(transaction);

            verify(mockTransactions).add(transaction);
        }

        private void given_add_transaction_will(PostActions postActions) {
            when(mockTransactions.add(any(Transaction.class))).thenReturn(postActions);
        }

        public class Success {

            @Test
            public void should_display_success_message() {
                given_add_transaction_will(success());

                submitTransactionAdd(transaction);

                verify(mockView).display("a success message");
                verify(mockView, never()).display("a failed message");
            }
        }

        public class Failed {

            @Test
            public void should_display_failed_message() {
                given_add_transaction_will(failed());

                submitTransactionAdd(transaction);

                verify(mockView, never()).display("a success message");
                verify(mockView).display("a failed message");
            }

        }

    }

    public class Valid {

        Transaction invalidTransaction = invalidTransaction();

        @Before
        public void given_has_some_field_error() {
            given_has_field_error(true);
        }

        @Test
        public void should_not_add_transaction() {
            submitTransactionAdd(invalidTransaction);

            verify(mockTransactions, never()).add(invalidTransaction);
        }

        @Test
        public void should_display_view() {
            assertThat(submitTransactionAdd(invalidTransaction)).isInstanceOf(PresentableAddTransaction.class);
        }

        private Transaction invalidTransaction() {
            return defaultTransaction().type(null).description(null).date(null).amount(null).build();
        }

    }

}
