package com.odde.bbuddy.transaction.domain.summary;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.TransactionsPostActions;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

import static java.util.stream.Collectors.toList;

public class SummaryOfTransactions implements TransactionsPostActions {

    private final List<TransactionForSummary> transactionForSummaries;

    public SummaryOfTransactions(List<Transaction> all) {
        this.transactionForSummaries = all.stream().map(TransactionForSummary::create).collect(toList());
    }

    @Override
    public void withSummary(Consumer<SummaryOfTransactions> consumer) {
        consumer.accept(this);
    }

    public int balance() {
        return sumOf(TransactionForSummary::balance);
    }

    public int totalIncome() {
        return sumOf(TransactionForSummary::income);
    }

    public int totalOutcome() {
        return sumOf(TransactionForSummary::outcome);
    }

    private int sumOf(ToIntFunction<TransactionForSummary> mapper) {
        return transactionForSummaries.stream().mapToInt(mapper).sum();
    }

}
