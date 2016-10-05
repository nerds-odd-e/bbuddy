package com.odde.bbuddy.transaction.view;

import com.odde.bbuddy.transaction.domain.SummaryOfTransactions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
public class PresentableSummaryOfTransactions extends ModelAndView {

    public void display(SummaryOfTransactions summaryOfTransactions) {
        addObject("balance", summaryOfTransactions.balance());
    }
}
