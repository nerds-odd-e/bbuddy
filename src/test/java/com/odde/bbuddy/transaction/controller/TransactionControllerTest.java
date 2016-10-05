package com.odde.bbuddy.transaction.controller;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.common.callback.PostActions;
import com.odde.bbuddy.common.view.Message;
import com.odde.bbuddy.transaction.domain.SummaryOfTransactions;
import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import com.odde.bbuddy.transaction.domain.TransactionsPostActions;
import com.odde.bbuddy.transaction.view.PresentableAddTransaction;
import com.odde.bbuddy.transaction.view.PresentableSummaryOfTransactions;
import com.odde.bbuddy.transaction.view.PresentableTransactions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.function.Consumer;

import static com.odde.bbuddy.common.Formats.parseDay;
import static com.odde.bbuddy.common.callback.PostActionsFactory.failed;
import static com.odde.bbuddy.common.callback.PostActionsFactory.success;
import static com.odde.bbuddy.transaction.domain.Transaction.Type.Outcome;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class TransactionControllerTest {

    Transactions mockTransactions = mock(Transactions.class);
    PresentableTransactions presentableTransactions = spy(new PresentableTransactions("whatever message"));
    PresentableSummaryOfTransactions presentableSummaryOfTransactions = spy(PresentableSummaryOfTransactions.class);
    Message mockMessage = mock(Message.class);
    TransactionController controller = new TransactionController(mockTransactions, new PresentableAddTransaction(), presentableTransactions, presentableSummaryOfTransactions, mockMessage);
    Transaction transaction = transaction(Outcome, "Outcome description", parseDay("2016-07-01"), 100);
    BindingResult stubBindingResult = mock(BindingResult.class);

    @Before
    public void given_has_no_field_error() {
        given_has_field_error(false);
    }

    public class Add {

        @Test
        public void should_display_view() {
            assertThat(controller.addTransaction()).isInstanceOf(PresentableAddTransaction.class);
        }

    }

    public class AddSubmitSuccess {

        @Before
        public void given_add_transaction_will_success() {
            given_add_transaction_will(success());
        }

        @Test
        public void should_display_view() {
            assertThat(submitTransactionAdd(transaction)).isInstanceOf(PresentableAddTransaction.class);
        }

        @Test
        public void should_add_transaction() {
            submitTransactionAdd(transaction);

            verify(mockTransactions).add(transaction);
        }

        @Test
        public void should_display_success_message() {
            controller.successMessage = "a success message";

            submitTransactionAdd(transaction);

            verify(mockMessage).display("a success message");
        }
    }

    public class AddSubmitFailed {

        @Test
        public void should_display_failed_message() {
            given_add_transaction_will(failed());
            controller.failedMessage = "a failed message";

            submitTransactionAdd(transaction);

            verify(mockMessage).display("a failed message");
        }

    }

    public class Valid {

        Transaction invalidTransaction = new Transaction();

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

    }

    public class List {

        SummaryOfTransactions summaryOfTransactions = new SummaryOfTransactions(asList());

        @Before
        public void given_transactions_processAll_is_ready_to_be_called() {
            given_transactions_processAll_will_return(transaction, summaryOfTransactions);
        }

        @Test
        public void should_display_view() {
            spyOnWithOfPresentableTransactions();

            assertThat(controller.index()).isSameAs(presentableTransactions);
            verify(presentableTransactions).with(presentableSummaryOfTransactions);
        }

        @Test
        public void should_let_view_display_transaction() {
            spyOnDisplayOfPresentableTransactions();

            controller.index();

            verify(presentableTransactions).display(transaction);
        }

        @Test
        public void should_let_view_display_summary_of_transactions() {
            spyOnDisplayOfPresentableSummaryOfTransactions();

            controller.index();

            verify(presentableSummaryOfTransactions).display(summaryOfTransactions);
        }

        private void spyOnDisplayOfPresentableSummaryOfTransactions() {
            doNothing().when(presentableSummaryOfTransactions).display(any(SummaryOfTransactions.class));
        }

        private void spyOnDisplayOfPresentableTransactions() {
            doNothing().when(presentableTransactions).display(any(Transaction.class));
        }

        private void spyOnWithOfPresentableTransactions() {
            doReturn(presentableTransactions).when(presentableTransactions).with(any(PresentableSummaryOfTransactions.class));
        }

        private void given_transactions_processAll_will_return(final Transaction transaction, SummaryOfTransactions summaryOfTransactions) {
            when(mockTransactions.processAll(any(Consumer.class))).thenAnswer(new Answer<TransactionsPostActions>() {
                @Override
                public TransactionsPostActions answer(InvocationOnMock invocation) throws Throwable {
                    Consumer<Transaction> consumer = invocation.getArgumentAt(0, Consumer.class);
                    consumer.accept(transaction);
                    return stubTransactionsPostActionsWith(summaryOfTransactions);
                }
            });
        }

        private TransactionsPostActions stubTransactionsPostActionsWith(SummaryOfTransactions summaryOfTransactions) {
            TransactionsPostActions stubTransactionsPostActions = mock(TransactionsPostActions.class);
            doAnswer(invocation -> {
                Consumer<SummaryOfTransactions> consumer = invocation.getArgumentAt(0, Consumer.class);
                consumer.accept(summaryOfTransactions);
                return null;
            }).when(stubTransactionsPostActions).withSummary(any(Consumer.class));
            return stubTransactionsPostActions;
        }

    }

    private void given_has_field_error(boolean value) {
        when(stubBindingResult.hasFieldErrors()).thenReturn(value);
    }

    private ModelAndView submitTransactionAdd(Transaction transaction) {
        return controller.submitAddTransaction(transaction, stubBindingResult);
    }

    private void given_add_transaction_will(PostActions postActions) {
        when(mockTransactions.add(any(Transaction.class))).thenReturn(postActions);
    }

    private Transaction transaction(Transaction.Type type, String description, Date date, int amount) {
        Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setDescription(description);
        transaction.setDate(date);
        transaction.setAmount(amount);
        return transaction;
    }

}
