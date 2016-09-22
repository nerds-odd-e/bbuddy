package com.odde.bbuddy.budget.view;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.odde.bbuddy.common.view.Messages.RESULT_MESSAGES_FULL_NAME;
import static java.lang.String.format;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class PresentableMonthlyBudgetAmount {

    @Value("${monthlybudget.totalamount.amount}")
    public String message;

    private final HttpServletRequest request;

    @Autowired
    public PresentableMonthlyBudgetAmount(HttpServletRequest request) {
        this.request = request;
    }

    public void display(long amount) {
        request.setAttribute("amount", format(message, amount));
    }
}
