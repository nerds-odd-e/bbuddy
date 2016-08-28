package com.odde.bbuddy.transaction.domain;

import com.odde.bbuddy.transaction.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Transactions {
    private final TransactionRepo repo;

    @Autowired
    public Transactions(TransactionRepo repo) {
        this.repo = repo;
    }

    public void add(Transaction transaction, Runnable afterSuccess, Runnable afterFailed) {
        try {
            repo.save(transaction);
            afterSuccess.run();
        } catch (IllegalArgumentException e) {
            afterFailed.run();
        }
    }
}
