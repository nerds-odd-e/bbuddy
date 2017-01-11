package com.odde.bbuddy.transaction.builder;

import com.odde.bbuddy.transaction.domain.summary.SummaryOfTransactions;
import com.odde.bbuddy.transaction.repo.Transaction;

import java.util.ArrayList;
import java.util.List;

import static com.odde.bbuddy.transaction.builder.TransactionBuilder.defaultTransaction;
import static com.odde.bbuddy.transaction.repo.Transaction.Type;
import static com.odde.bbuddy.transaction.repo.Transaction.Type.Income;
import static com.odde.bbuddy.transaction.repo.Transaction.Type.Outcome;

public class SummaryOfTransactionsBuilder {

    private final List<Transaction> all = new ArrayList<>();

    public static SummaryOfTransactionsBuilder builder() {
        return new SummaryOfTransactionsBuilder();
    }

    public SummaryOfTransactionsBuilder incomeWithAmount(int amount) {
        addTransaction(amount, Income);
        return this;
    }

    public SummaryOfTransactionsBuilder outcomeWithAmount(int amount) {
        addTransaction(amount, Outcome);
        return this;
    }

    public SummaryOfTransactions build() {
        return new SummaryOfTransactions(all);
    }

    private boolean addTransaction(int amount, Type type) {
        return all.add(defaultTransaction().type(type).amount(amount).build());
    }

}
