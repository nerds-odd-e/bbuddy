package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import com.odde.bbuddy.transaction.domain.TransactionsPostActions;
import com.odde.bbuddy.transaction.domain.summary.SummaryOfTransactions;
import com.odde.bbuddy.transaction.view.PresentableSummaryOfTransactions;
import com.odde.bbuddy.transaction.view.PresentableTransactions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.web.servlet.ModelAndView;

import java.util.function.Consumer;

import static com.odde.bbuddy.common.controller.ControllerTestHelper.spyOnDisplayOf;
import static com.odde.bbuddy.transaction.builder.PresentableSummaryOfTransactionsBuilder.defaultPresentableSummaryOfTransactions;
import static com.odde.bbuddy.transaction.builder.PresentableTransactionsBuilder.defaultPresentableTransactions;
import static com.odde.bbuddy.transaction.builder.SummaryOfTransactionsBuilder.builder;
import static com.odde.bbuddy.transaction.builder.TransactionBuilder.defaultTransaction;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TransactionListControllerTest {

    SummaryOfTransactions summaryOfTransactions = builder().build();
    Transaction transaction = defaultTransaction().build();
    Transactions mockTransactions = mock(Transactions.class);
    PresentableTransactions presentableTransactions = spy(defaultPresentableTransactions().build());
    PresentableSummaryOfTransactions presentableSummaryOfTransactions = spy(defaultPresentableSummaryOfTransactions().build());
    TransactionListController controller = new TransactionListController(mockTransactions, presentableTransactions, presentableSummaryOfTransactions);

    @Before
    public void given_transactions_processAll_is_ready_to_be_called() {
        given_transactions_processAll_will_return(transaction, summaryOfTransactions);
    }

    @Test
    public void should_display_view() {
        assertThat(list()).isInstanceOf(PresentableTransactions.class);
    }

    private ModelAndView list() {
        return controller.index(1);
    }

    @Test
    public void should_let_view_display_transaction() {
        spyOnDisplayOf(presentableTransactions);

        list();

        verify(presentableTransactions).display(transaction);
    }

    @Test
    public void should_let_view_display_summary_of_transactions() {
        spyOnDisplayOf(presentableSummaryOfTransactions);

        list();

        verify(presentableSummaryOfTransactions).display(summaryOfTransactions);
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
