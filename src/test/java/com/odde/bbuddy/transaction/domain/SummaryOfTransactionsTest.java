package com.odde.bbuddy.transaction.domain;

import com.odde.bbuddy.transaction.domain.summary.SummaryOfTransactions;
import org.junit.Test;

import static com.odde.bbuddy.transaction.builder.SummaryOfTransactionsBuilder.builder;
import static org.assertj.core.api.Assertions.assertThat;

public class SummaryOfTransactionsTest {

    private final int whateverAmount = 1000;
    private SummaryOfTransactions summaryOfTransactions;

    @Test
    public void total_of_income() {
        summaryOfTransactions = builder()
                .incomeWithAmount(100).incomeWithAmount(200).build();

        assertThat(summaryOfTransactions.totalIncome()).isEqualTo(300);
    }

    @Test
    public void total_of_income_not_including_any_outcome() {
        summaryOfTransactions = builder()
                .incomeWithAmount(100).incomeWithAmount(200)
                .outcomeWithAmount(whateverAmount).build();

        assertThat(summaryOfTransactions.totalIncome()).isEqualTo(300);
    }

    @Test
    public void total_of_outcome() {
        summaryOfTransactions = builder()
                .outcomeWithAmount(100).outcomeWithAmount(200).build();

        assertThat(summaryOfTransactions.totalOutcome()).isEqualTo(300);
    }

    @Test
    public void total_of_outcome_not_including_any_income() {
        summaryOfTransactions = builder()
                .outcomeWithAmount(100).outcomeWithAmount(200)
                .incomeWithAmount(whateverAmount).build();

        assertThat(summaryOfTransactions.totalOutcome()).isEqualTo(300);
    }

    @Test
    public void balance() {
        summaryOfTransactions = builder()
                .incomeWithAmount(200).outcomeWithAmount(50).build();

        assertThat(summaryOfTransactions.balance()).isEqualTo(150);
    }

}
