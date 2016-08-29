package com.odde.bbuddy.transaction.domain;

import com.odde.bbuddy.transaction.repo.TransactionRepo;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TransactionsTest {

    TransactionRepo mockRepo = mock(TransactionRepo.class);
    Transactions transactions = new Transactions(mockRepo);
    Transaction transaction = new Transaction();

    Runnable afterSuccess = mock(Runnable.class);
    Runnable afterFailed = mock(Runnable.class);

    @Test
    public void save_transaction() {
        transactions.add(transaction);

        verify(mockRepo).save(transaction);
    }

    @Test
    public void call_after_success_when_save_successfully() {
        transactions.add(transaction)
                .success(afterSuccess)
                .failed(afterFailed);

        verify(afterSuccess).run();
        verify(afterFailed, never()).run();
    }

    @Test
    public void call_after_failed_when_save_failed() {
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
