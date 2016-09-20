package com.odde.bbuddy.transaction.view;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.function.Consumer;

import static com.odde.bbuddy.common.Formats.parseDay;
import static com.odde.bbuddy.transaction.domain.Transaction.Type;
import static com.odde.bbuddy.transaction.domain.Transaction.Type.Income;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class PresentableTransactionsTest {

    Transactions stubTransactions = mock(Transactions.class);
    PresentableTransactions presentableTransactions = new PresentableTransactions(stubTransactions);
    Model mockModel = mock(Model.class);

    public class NoTransaction {

        @Before
        public void given_has_no_transaction() {
            given_has_transaction();
        }

        @Test
        public void should_hide_list_view() {
            display();

            assertThat(presentableTransactions.hidden()).isEqualTo("hidden");
        }

        @Test
        public void should_show_message() {
            presentableTransactions.noTransactionMessage = "no transaction message";

            display();

            assertThat(presentableTransactions.message()).isEqualTo("no transaction message");
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
            display();

            assertThat(presentableTransactions.hidden()).isEqualTo("");
        }

        @Test
        public void should_not_show_message() {
            display();

            assertThat(presentableTransactions.message()).isEqualTo("");
        }

        @Test
        public void should_pass_transaction_to_page() {
            display();

            verify(mockModel).addAttribute("transactions", presentableTransactions);
            assertThat(presentableTransactions)
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

    private void display() {
        presentableTransactions.display(mockModel);
    }

}
