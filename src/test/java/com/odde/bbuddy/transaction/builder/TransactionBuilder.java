package com.odde.bbuddy.transaction.builder;

import com.odde.bbuddy.transaction.domain.Transaction;

import static com.odde.bbuddy.common.Formats.parseDay;
import static com.odde.bbuddy.transaction.domain.Transaction.Type.Income;
import static com.odde.bbuddy.transaction.domain.Transaction.builder;

public class TransactionBuilder {

    public static Transaction.TransactionBuilder defaultTransaction() {
        return builder()
                .type(Income).description("description")
                .date(parseDay("2016-07-01")).amount(100);
    }

}
