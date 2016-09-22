package com.odde.bbuddy.budget.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import static com.odde.bbuddy.common.view.Messages.RESULT_MESSAGES_FULL_NAME;
import static java.lang.String.format;

@Component
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class PresentableMonthlyBudgetAmount {

    @Value("${monthlybudget.totalamount.amount}")
    public String message;

    public void display(Model model, long amount) {
        model.addAttribute("amount", format(message, amount));
    }
}
