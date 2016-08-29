package com.odde.bbuddy.transaction.domain;

import com.odde.bbuddy.common.PostActions;
import com.odde.bbuddy.common.PostActionsFactory;
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

    public PostActions add(Transaction transaction) {
        try {
            repo.save(transaction);
            return PostActionsFactory.success();
        } catch (IllegalArgumentException e) {
            return PostActionsFactory.failed();
        }
    }
}
