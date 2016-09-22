package com.odde.bbuddy.transaction.view;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import static com.odde.bbuddy.common.view.Messages.RESULT_MESSAGES_FULL_NAME;
import static com.odde.bbuddy.transaction.domain.Transaction.Type;

@Component
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class PresentableAddTransaction {

    public void display(Model model, Type[] values) {
        model.addAttribute("types", values);
    }
}
