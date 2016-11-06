package com.odde.bbuddy.transaction.domain.summary;

import com.odde.bbuddy.transaction.domain.Transaction;

public class OutcomeTransactionForSummary extends TransactionForSummary {
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
