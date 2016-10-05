package com.odde.bbuddy.transaction.domain.summary;

import com.odde.bbuddy.transaction.domain.Transaction;

public class TransactionForSummary {

    public int income() {
        return 0;
    }

    public int outcome() {
        return 0;
    }

    public int balance() {
        return 0;
    }

    public static TransactionForSummary create(Transaction transaction) {
        switch (transaction.getType()) {
            case Income:
                return new IncomeTransactionForSummary(transaction);
            case Outcome:
                return new OutcomeTransactionForSummary(transaction);
            default:
                throw new IllegalStateException();
        }
    }
}
