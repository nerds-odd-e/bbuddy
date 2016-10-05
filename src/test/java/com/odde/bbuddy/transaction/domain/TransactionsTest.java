package com.odde.bbuddy.transaction.domain;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.transaction.domain.summary.SummaryOfTransactions;
import com.odde.bbuddy.transaction.repo.TransactionRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.function.Consumer;

import static com.odde.bbuddy.common.Formats.parseDay;
import static com.odde.bbuddy.transaction.domain.Transaction.*;
import static com.odde.bbuddy.transaction.domain.Transaction.Type.*;
import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class TransactionsTest {

    TransactionRepo mockRepo = mock(TransactionRepo.class);
    Transactions transactions = new Transactions(mockRepo);
    Transaction transaction = transaction(Income, "any description", parseDay("2016-07-01"), 100);

    Runnable afterSuccess = mock(Runnable.class);
    Runnable afterFailed = mock(Runnable.class);

    public class Save {

        @Test
        public void should_save_transaction() {
            transactions.add(transaction);

            verify(mockRepo).save(transaction);
        }

        @Test
        public void should_call_after_success_when_save_successfully() {
            transactions.add(transaction)
                    .success(afterSuccess)
                    .failed(afterFailed);

            verify(afterSuccess).run();
            verify(afterFailed, never()).run();
        }

        @Test
        public void should_call_after_failed_when_save_failed() {
            given_save_will_fail();

            transactions.add(transaction)
                    .success(afterSuccess)
                    .failed(afterFailed);

            verify(afterFailed).run();
            verify(afterSuccess, never()).run();
        }

        private void given_save_will_fail() {
            doThrow(IllegalArgumentException.class).when(mockRepo).save(any(Transaction.class));
        }

    }

    public class ProcessAll {

        private Consumer<Transaction> whateverConsumer = transaction -> {};

        @Before
        public void given_findAll_will_return_transaction(){
            given_findAll_will_return(transaction);
        }

        @Test
        public void should_process_all_transactions() {
            Consumer<Transaction> mockConsumer = mock(Consumer.class);

            transactions.processAll(mockConsumer);

            verify(mockConsumer).accept(transaction);
        }

        @Test
        public void process_all_transactions_with_summary() {
            Consumer<SummaryOfTransactions> mockConsumer = mock(Consumer.class);

            transactions.processAll(whateverConsumer)
                    .withSummary(mockConsumer);

            verify(mockConsumer).accept(any(SummaryOfTransactions.class));
        }

        private void given_findAll_will_return(Transaction transaction) {
            when(mockRepo.findAll()).thenReturn(asList(transaction));
        }

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
