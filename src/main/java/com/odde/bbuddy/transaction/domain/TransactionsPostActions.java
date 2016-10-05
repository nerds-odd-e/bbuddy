package com.odde.bbuddy.transaction.domain;

import java.util.function.Consumer;

public interface TransactionsPostActions {
    void withSummary(Consumer<SummaryOfTransactions> consumer);
}
