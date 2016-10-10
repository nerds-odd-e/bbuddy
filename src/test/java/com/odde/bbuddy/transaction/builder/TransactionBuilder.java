package com.odde.bbuddy.transaction.builder;

import com.odde.bbuddy.transaction.domain.Transaction;

import java.util.List;

import static com.odde.bbuddy.common.Formats.parseDay;
import static com.odde.bbuddy.transaction.domain.Transaction.Type.Income;
import static com.odde.bbuddy.transaction.domain.Transaction.builder;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class TransactionBuilder {

    private int count;

    public static Transaction.TransactionBuilder defaultTransaction() {
        return builder()
                .type(Income).description("description")
                .date(parseDay("2016-07-01")).amount(100);
    }

    public static TransactionBuilder defaultTransactions(int count) {
        return new TransactionBuilder().count(count);
    }

    public TransactionBuilder count(int count) {
        this.count = count;
        return this;
    }

    public List<Transaction> buildAll() {
        return range(0, count).mapToObj(i -> defaultTransaction().build()).collect(toList());
    }
}
