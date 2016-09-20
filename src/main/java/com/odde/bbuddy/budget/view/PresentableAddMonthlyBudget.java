package com.odde.bbuddy.budget.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import static com.odde.bbuddy.common.view.Messages.RESULT_MESSAGES;

@Component
@PropertySource(RESULT_MESSAGES)
public class PresentableAddMonthlyBudget {

    @Value("${label.month}")
    String month;

    @Value("${label.budget}")
    String budget;

    @Value("${label.add}")
    String add;

    public void display(Model model) {
        model.addAttribute("label.month", month);
        model.addAttribute("label.budget", budget);
        model.addAttribute("label.add", add);
    }
}
