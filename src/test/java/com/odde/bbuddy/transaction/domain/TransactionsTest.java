package com.odde.bbuddy.transaction.domain;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.common.builder.ConsumeAnswer;
import com.odde.bbuddy.transaction.domain.summary.SummaryOfTransactions;
import com.odde.bbuddy.transaction.repo.Transaction;
import com.odde.bbuddy.transaction.repo.TransactionRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.function.Consumer;

import static com.odde.bbuddy.common.builder.PageableBuilder.builder;
import static com.odde.bbuddy.transaction.builder.TransactionBuilder.defaultTransaction;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class TransactionsTest {

    TransactionRepo mockRepo = mock(TransactionRepo.class);
    Transactions transactions = new Transactions(mockRepo);
    Transaction transaction = defaultTransaction().build();

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

        Page mockPage = mock(Page.class);
        private Consumer<Transaction> whateverTransactionConsumer = transaction -> {};

        @Before
        public void given_findAll_will_return_transaction() {
            given_findAll_will_return(transaction);
        }

        @Test
        public void should_process_all_transactions() {
            Consumer<Transaction> mockConsumer = mock(Consumer.class);

            processAll(mockConsumer);

            verify(mockConsumer).accept(transaction);
        }

        @Test
        public void process_all_transactions_with_summary() {
            Consumer<SummaryOfTransactions> mockConsumer = mock(Consumer.class);

            processAll(whateverTransactionConsumer)
                    .withSummary(mockConsumer);

            verify(mockConsumer).accept(any(SummaryOfTransactions.class));
        }

        @Test
        public void should_pass_pageable_to_repo() {
            Pageable pageable = builder().build();

            transactions.processAll(whateverTransactionConsumer, pageable);

            verify(mockRepo).findAll(pageable);
        }
        
        @Test
        public void should_process_all_transactions_with_total_page_count() {
            given_total_page_count_is(10);
            Consumer<Integer> mockConsumer = mock(Consumer.class);

            processAll(whateverTransactionConsumer)
                    .withTotalPageCount(mockConsumer);

            verify(mockConsumer).accept(10);
        }

        private void given_total_page_count_is(int totalPageCount) {
            when(mockPage.getTotalPages()).thenReturn(totalPageCount);
        }

        private void given_findAll_will_return(Transaction transaction) {
            doAnswer(
                    new ConsumeAnswer<>(transaction)
            ).when(mockPage).forEach(any(Consumer.class));
            when(mockRepo.findAll(any(Pageable.class))).thenReturn(mockPage);
        }

        private TransactionsPostActions processAll(Consumer<Transaction> mockConsumer) {
            return transactions.processAll(mockConsumer, builder().build());
        }

    }

}
