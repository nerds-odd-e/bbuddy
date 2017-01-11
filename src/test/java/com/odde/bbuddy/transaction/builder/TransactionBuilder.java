package com.odde.bbuddy.transaction.builder;

import com.odde.bbuddy.transaction.repo.Transaction;

import static com.odde.bbuddy.common.Formats.parseDay;
import static com.odde.bbuddy.transaction.repo.Transaction.Type.Income;
import static com.odde.bbuddy.transaction.repo.Transaction.builder;

public class TransactionBuilder {

    public static Transaction.TransactionBuilder defaultTransaction() {
        return builder()
                .type(Income).description("description")
                .date(parseDay("2016-07-01")).amount(100);
    }

}
