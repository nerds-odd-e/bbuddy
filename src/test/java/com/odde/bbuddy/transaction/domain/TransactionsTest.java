package com.odde.bbuddy.transaction.domain;

import com.odde.bbuddy.transaction.repo.TransactionRepo;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TransactionsTest {

    @Test
    public void save_transaction() {
        TransactionRepo repo = mock(TransactionRepo.class);
        Transactions transactions = new Transactions(repo);

        Transaction transaction = new Transaction();
        transactions.add(transaction, ()->{});

        verify(repo).save(transaction);
    }
}
