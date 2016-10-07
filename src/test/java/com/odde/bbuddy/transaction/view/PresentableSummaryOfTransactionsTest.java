package com.odde.bbuddy.transaction.view;

import com.odde.bbuddy.transaction.domain.summary.SummaryOfTransactions;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import static com.odde.bbuddy.transaction.builder.PresentableSummaryOfTransactionsBuilder.defaultPresentableSummaryOfTransactions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PresentableSummaryOfTransactionsTest {

    PresentableSummaryOfTransactions presentableSummaryOfTransactions;
    SummaryOfTransactions mockSummaryOfTransactions = mock(SummaryOfTransactions.class);

    @Test
    public void should_show_balance() {
        presentableSummaryOfTransactions = defaultPresentableSummaryOfTransactions()
                .balanceMessage("Balance is %s").build();
        given_balance_is(100);

        display();

        assertThat(modelOfPresentableSummaryOfTransactions()).containsEntry("balance", "Balance is 100");
    }

    @Test
    public void should_show_total_income() {
        presentableSummaryOfTransactions = defaultPresentableSummaryOfTransactions()
                .totalIncomeMessage("Total income is %s").build();
        given_total_income_is(100);

        display();

        assertThat(modelOfPresentableSummaryOfTransactions()).containsEntry("totalIncome", "Total income is 100");
    }

    @Test
    public void should_show_total_outcome() {
        presentableSummaryOfTransactions = defaultPresentableSummaryOfTransactions()
                .totalOutcomeMessage("Total outcome is %s").build();
        given_total_outcome_is(100);

        display();

        assertThat(modelOfPresentableSummaryOfTransactions()).containsEntry("totalOutcome", "Total outcome is 100");
    }

    private void given_total_outcome_is(int amount) {
        when(mockSummaryOfTransactions.totalOutcome()).thenReturn(amount);
    }

    private void given_total_income_is(int amount) {
        when(mockSummaryOfTransactions.totalIncome()).thenReturn(amount);
    }

    private ModelMap modelOfPresentableSummaryOfTransactions() {
        return presentableSummaryOfTransactions.getModelMap();
    }

    private void given_balance_is(int amount) {
        when(mockSummaryOfTransactions.balance()).thenReturn(amount);
    }

    private void display() {
        presentableSummaryOfTransactions.display(mockSummaryOfTransactions);
    }

}
