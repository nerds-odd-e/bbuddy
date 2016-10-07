package com.odde.bbuddy.transaction.domain;

import com.odde.bbuddy.transaction.domain.summary.SummaryOfTransactions;
import org.junit.Test;

import static com.odde.bbuddy.transaction.builder.TransactionBuilder.defaultTransaction;
import static com.odde.bbuddy.transaction.domain.Transaction.Type.Income;
import static com.odde.bbuddy.transaction.domain.Transaction.Type.Outcome;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class SummaryOfTransactionsTest {

    private final int whateverAmount = 1000;

    @Test
    public void total_of_income() {
        SummaryOfTransactions summaryOfTransactions = new SummaryOfTransactions(asList(
                incomeTransactionWithAmount(100),
                incomeTransactionWithAmount(200)));

        assertThat(summaryOfTransactions.totalIncome()).isEqualTo(300);
    }

    @Test
    public void total_of_income_not_including_any_outcome() {
        SummaryOfTransactions summaryOfTransactions = new SummaryOfTransactions(asList(
                incomeTransactionWithAmount(100),
                incomeTransactionWithAmount(200),
                outcomeTransactionWithAmount(whateverAmount)));

        assertThat(summaryOfTransactions.totalIncome()).isEqualTo(300);
    }

    @Test
    public void total_of_outcome() {
        SummaryOfTransactions summaryOfTransactions = new SummaryOfTransactions(asList(
                outcomeTransactionWithAmount(100),
                outcomeTransactionWithAmount(200)));

        assertThat(summaryOfTransactions.totalOutcome()).isEqualTo(300);
    }

    @Test
    public void total_of_outcome_not_including_any_income() {
        SummaryOfTransactions summaryOfTransactions = new SummaryOfTransactions(asList(
                outcomeTransactionWithAmount(100),
                outcomeTransactionWithAmount(200),
                incomeTransactionWithAmount(whateverAmount)));

        assertThat(summaryOfTransactions.totalOutcome()).isEqualTo(300);
    }

    @Test
    public void balance() {
        SummaryOfTransactions summaryOfTransactions = new SummaryOfTransactions(asList(
                incomeTransactionWithAmount(200),
                outcomeTransactionWithAmount(50)));

        assertThat(summaryOfTransactions.balance()).isEqualTo(150);
    }

    private Transaction outcomeTransactionWithAmount(int whateverAmount) {
        return defaultTransaction().type(Outcome).amount(whateverAmount).build();
    }

    private Transaction incomeTransactionWithAmount(int amount) {
        return defaultTransaction().type(Income).amount(amount).build();
    }

}
