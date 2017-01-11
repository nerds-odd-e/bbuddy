package com.odde.bbuddy.transaction.domain;

import com.odde.bbuddy.common.callback.PostActions;
import com.odde.bbuddy.transaction.domain.summary.SummaryOfTransactions;
import com.odde.bbuddy.transaction.repo.Transaction;
import com.odde.bbuddy.transaction.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public TransactionsPostActions processAll(Consumer<Transaction> consumer, Pageable pageable) {
        Page<Transaction> all = repo.findAll(pageable);
        all.forEach(consumer::accept);
        return new TransactionsPostActions() {
            @Override
            public TransactionsPostActions withSummary(Consumer<SummaryOfTransactions> consumer) {
                consumer.accept(new SummaryOfTransactions(all.getContent()));
                return this;
            }

            @Override
            public TransactionsPostActions withTotalPageCount(Consumer<Integer> consumer) {
                consumer.accept(all.getTotalPages());
                return this;
            }
        };
    }

}
