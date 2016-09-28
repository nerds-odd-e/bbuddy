package com.odde.bbuddy.transaction.view;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.odde.bbuddy.common.Formats.parseDay;
import static com.odde.bbuddy.common.controller.Urls.TRANSACTION_INDEX;
import static com.odde.bbuddy.transaction.domain.Transaction.Type;
import static com.odde.bbuddy.transaction.domain.Transaction.Type.Income;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

@RunWith(NestedRunner.class)
public class PresentableTransactionsTest {

    Transactions stubTransactions = mock(Transactions.class);

    @Test
    public void should_go_to_index_page() {
        assertThat(presentableTransactions().getViewName()).isEqualTo(TRANSACTION_INDEX);
    }

    public class NoTransaction {

        @Before
        public void given_has_no_transaction() {
            given_has_transaction();
        }

        @Test
        public void should_hide_list_view() {
            assertThat(modelOfPresentableTransactions().get("hidden")).isEqualTo("hidden");
        }

        @Test
        public void should_show_message() {
            PresentableTransactions presentableTransactions = new PresentableTransactions(stubTransactions, "no transaction message");

            assertThat(presentableTransactions.getModel().get("message")).isEqualTo("no transaction message");
        }

    }

    public class HasSomeTransactions {

        private Date date = parseDay("2016-07-01");
        private int amount = 100;

        @Before
        public void given_has_some_transaction() {
            given_has_transaction(transaction(Income, "description", date, amount));
        }

        @Test
        public void should_not_hide_list_view() {
            assertThat(modelOfPresentableTransactions()).doesNotContainKey("hidden");
        }

        @Test
        public void should_not_show_message() {
            assertThat(modelOfPresentableTransactions()).doesNotContainKey("message");
        }

        @Test
        public void should_pass_transaction_to_page() {
            assertThat((List<PresentableTransaction>)modelOfPresentableTransactions().get("transactions"))
                    .usingFieldByFieldElementComparator()
                    .containsExactly(presentableTransaction(Income, "description", date, amount));
        }

        private PresentableTransaction presentableTransaction(Type type, String description, Date date, int amount) {
            PresentableTransaction presentableTransaction = new PresentableTransaction();
            presentableTransaction.setType(type);
            presentableTransaction.setDescription(description);
            presentableTransaction.setDate(date);
            presentableTransaction.setAmount(amount);
            return presentableTransaction;
        }

        private Transaction transaction(Type type, String description, Date date, int amount) {
            Transaction transaction = new Transaction();
            transaction.setType(type);
            transaction.setDescription(description);
            transaction.setDate(date);
            transaction.setAmount(amount);
            return transaction;
        }

    }

    private void given_has_transaction(Transaction... transactions) {
        doAnswer(invocation -> {
            Consumer<Transaction> consumer = invocation.getArgumentAt(0, Consumer.class);
            asList(transactions).forEach(consumer::accept);
            return null;
        }).when(stubTransactions).processAll(any(Consumer.class));
    }

    private Map<String, Object> modelOfPresentableTransactions() {
        return presentableTransactions().getModel();
    }

    private PresentableTransactions presentableTransactions() {
        return new PresentableTransactions(stubTransactions, "whatever message");
    }

}
