package com.odde.bbuddy.transaction.domain.summary;

import com.odde.bbuddy.transaction.repo.Transaction;

public interface TransactionForSummary {

    static TransactionForSummary create(Transaction transaction) {
        switch (transaction.getType()) {
            case Income:
                return new IncomeTransactionForSummary(transaction);
            case Outcome:
                return new OutcomeTransactionForSummary(transaction);
            default:
                throw new IllegalStateException();
        }
    }

    int income();

    int outcome();

    int balance();
}
