package com.odde.bbuddy.transaction.domain;

import com.odde.bbuddy.transaction.domain.summary.SummaryOfTransactions;

import java.util.function.Consumer;

public interface TransactionsPostActions {
    TransactionsPostActions withSummary(Consumer<SummaryOfTransactions> consumer);

    TransactionsPostActions withTotalPageCount(Consumer<Integer> consumer);
}
