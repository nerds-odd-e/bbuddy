package com.odde.bbuddy.transaction.domain.summary;

import com.odde.bbuddy.transaction.repo.Transaction;

public class IncomeTransactionForSummary implements TransactionForSummary {

    private final Transaction origin;

    public IncomeTransactionForSummary(Transaction transaction) {
        origin = transaction;
    }

    @Override
    public int income() {
        return origin.getAmount();
    }

    @Override
    public int outcome() {
        return 0;
    }

    @Override
    public int balance() {
        return origin.getAmount();
    }
}
