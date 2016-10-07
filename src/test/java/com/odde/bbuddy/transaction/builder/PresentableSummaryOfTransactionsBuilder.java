package com.odde.bbuddy.transaction.builder;

import com.odde.bbuddy.transaction.view.PresentableSummaryOfTransactions;

import static com.odde.bbuddy.transaction.view.PresentableSummaryOfTransactions.*;

public class PresentableSummaryOfTransactionsBuilder {

    public static PresentableSummaryOfTransactions.PresentableSummaryOfTransactionsBuilder defaultPresentableSummaryOfTransactions() {
        return builder()
                .balanceMessage("whatever message")
                .totalIncomeMessage("whatever message")
                .totalOutcomeMessage("whatever message");
    }

}
