package com.odde.bbuddy.transaction.view;

import com.odde.bbuddy.transaction.domain.summary.SummaryOfTransactions;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;
import static java.lang.String.format;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
@PropertySource(RESULT_MESSAGES_FULL_NAME)
@Builder
public class PresentableSummaryOfTransactions extends ModelAndView {

    private final String balanceMessage;
    private final String totalIncomeMessage;
    private final String totalOutcomeMessage;

    public PresentableSummaryOfTransactions(
            @Value("${summary.transaction.balance}") String balanceMessage,
            @Value("${summary.transaction.totalIncome}") String totalIncomeMessage,
            @Value("${summary.transaction.totalOutcome}") String totalOutcomeMessage) {
        this.balanceMessage = balanceMessage;
        this.totalIncomeMessage = totalIncomeMessage;
        this.totalOutcomeMessage = totalOutcomeMessage;
    }

    public void display(SummaryOfTransactions summaryOfTransactions) {
        addObject("balance", format(balanceMessage, summaryOfTransactions.balance()));
        addObject("totalIncome", format(totalIncomeMessage, summaryOfTransactions.totalIncome()));
        addObject("totalOutcome", format(totalOutcomeMessage, summaryOfTransactions.totalOutcome()));
    }
}
