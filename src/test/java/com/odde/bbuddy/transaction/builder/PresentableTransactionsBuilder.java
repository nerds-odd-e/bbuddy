package com.odde.bbuddy.transaction.builder;

import com.odde.bbuddy.transaction.view.PresentableTransactions;

import static com.odde.bbuddy.transaction.view.PresentableTransactions.builder;

public class PresentableTransactionsBuilder {

    public static PresentableTransactions.PresentableTransactionsBuilder defaultPresentableTransactions() {
        return builder().noTransactionMessage("whatever message");
    }
}
