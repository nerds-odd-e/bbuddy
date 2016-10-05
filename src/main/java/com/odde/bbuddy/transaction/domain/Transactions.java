package com.odde.bbuddy.transaction.domain;

import com.odde.bbuddy.common.callback.PostActions;
import com.odde.bbuddy.transaction.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

import static com.odde.bbuddy.common.callback.PostActionsFactory.failed;
import static com.odde.bbuddy.common.callback.PostActionsFactory.success;

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
            return success();
        } catch (IllegalArgumentException e) {
            return failed();
        }
    }

    public TransactionsPostActions processAll(Consumer<Transaction> consumer) {
        List<Transaction> all = repo.findAll();
        all.forEach(consumer::accept);
        return new SummaryOfTransactions(all);
    }

}
