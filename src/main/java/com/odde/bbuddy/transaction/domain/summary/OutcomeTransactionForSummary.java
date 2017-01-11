package com.odde.bbuddy.transaction.domain.summary;

import com.odde.bbuddy.transaction.repo.Transaction;

public class OutcomeTransactionForSummary implements TransactionForSummary {
    private final Transaction origin;

    public OutcomeTransactionForSummary(Transaction transaction) {
        origin = transaction;
    }

    @Override
    public int income() {
        return 0;
    }

    @Override
    public int outcome() {
        return origin.getAmount();
    }

    @Override
    public int balance() {
        return -origin.getAmount();
    }
}
