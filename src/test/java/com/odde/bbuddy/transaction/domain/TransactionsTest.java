package com.odde.bbuddy.transaction.domain;

import com.odde.bbuddy.transaction.repo.TransactionRepo;
import org.junit.Test;

import static com.odde.bbuddy.transaction.domain.RunnableHelper.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TransactionsTest {

    TransactionRepo repo = mock(TransactionRepo.class);
    Transactions transactions = new Transactions(repo);
    Transaction transaction = new Transaction();

    @Test
    public void save_transaction() {
        transactions.add(transaction, WHATEVER);

        verify(repo).save(transaction);
    }

    @Test
    public void call_after_success_when_save_successfully() {
        Runnable afterSuccess = mock(Runnable.class);
        transactions.add(transaction, afterSuccess);

        verify(afterSuccess).run();
    }
}
