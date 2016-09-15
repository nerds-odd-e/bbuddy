package com.odde.bbuddy.transaction.domain;

import com.odde.bbuddy.common.callback.PostActions;
import com.odde.bbuddy.transaction.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Consumer;

import static com.odde.bbuddy.common.Formats.parseDay;
import static com.odde.bbuddy.common.callback.PostActionsFactory.failed;
import static com.odde.bbuddy.common.callback.PostActionsFactory.success;
import static com.odde.bbuddy.transaction.domain.Transaction.Type.Income;
import static com.odde.bbuddy.transaction.domain.Transaction.Type.Outcome;

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

    public void processAll(Consumer<Transaction> consumer) {
        consumer.accept(transaction(Income, "Course Registration", parseDay("2016-08-14"), 4000));
        consumer.accept(transaction(Outcome, "Buy MacBook Pro", parseDay("2015-11-01"), 100));
    }

    private Transaction transaction(Transaction.Type type, String description, Date date, int amount) {
        Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setDescription(description);
        transaction.setDate(date);
        transaction.setAmount(amount);
        return transaction;
    }
}
