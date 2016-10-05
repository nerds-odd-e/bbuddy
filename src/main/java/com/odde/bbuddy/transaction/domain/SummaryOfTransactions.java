package com.odde.bbuddy.transaction.domain;

import java.util.List;
import java.util.function.Consumer;

import static com.odde.bbuddy.transaction.domain.Transaction.Type.Income;
import static com.odde.bbuddy.transaction.domain.Transaction.Type.Outcome;

public class SummaryOfTransactions implements TransactionsPostActions {

    private final List<Transaction> all;

    public SummaryOfTransactions(List<Transaction> all) {
        this.all = all;
    }

    @Override
    public void withSummary(Consumer<SummaryOfTransactions> consumer) {
        consumer.accept(this);
    }

    public int balance() {
        return totalIncome() - totalOutcome();
    }

    public int totalIncome() {
        return sumOf(Income);
    }

    public int totalOutcome() {
        return sumOf(Outcome);
    }

    private int sumOf(Transaction.Type type) {
        return all.stream()
                .filter(transaction -> transaction.getType() == type)
                .mapToInt(Transaction::getAmount).sum();
    }

}
