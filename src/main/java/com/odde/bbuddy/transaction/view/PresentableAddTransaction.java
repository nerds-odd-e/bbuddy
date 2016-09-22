package com.odde.bbuddy.transaction.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import static com.odde.bbuddy.common.view.Messages.RESULT_MESSAGES_FULL_NAME;
import static com.odde.bbuddy.transaction.domain.Transaction.Type;

@Component
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class PresentableAddTransaction {

    @Value("${label.add}")
    String add;

    public void display(Model model, Type[] values) {
        model.addAttribute("types", values);
        model.addAttribute("label.add", add);
    }
}
