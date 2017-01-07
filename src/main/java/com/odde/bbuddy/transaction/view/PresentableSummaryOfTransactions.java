package com.odde.bbuddy.transaction.view;

import com.odde.bbuddy.common.view.View;
import com.odde.bbuddy.transaction.domain.summary.SummaryOfTransactions;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;
import static java.lang.String.format;

@Component
@RequestScope
@PropertySource(RESULT_MESSAGES_FULL_NAME)
@Builder
public class PresentableSummaryOfTransactions extends ModelAndView implements View<SummaryOfTransactions> {

    private final String balanceMessage;
    private final String totalIncomeMessage;
    private final String totalOutcomeMessage;

    public PresentableSummaryOfTransactions(
            @Value("${transactions.summary.balance}") String balanceMessage,
            @Value("${transactions.summary.totalIncome}") String totalIncomeMessage,
            @Value("${transactions.summary.totalOutcome}") String totalOutcomeMessage) {
        this.balanceMessage = balanceMessage;
        this.totalIncomeMessage = totalIncomeMessage;
        this.totalOutcomeMessage = totalOutcomeMessage;
    }

    @Override
    public void display(SummaryOfTransactions summaryOfTransactions) {
        addObject("balance", format(balanceMessage, summaryOfTransactions.balance()));
        addObject("totalIncome", format(totalIncomeMessage, summaryOfTransactions.totalIncome()));
        addObject("totalOutcome", format(totalOutcomeMessage, summaryOfTransactions.totalOutcome()));
    }
}
