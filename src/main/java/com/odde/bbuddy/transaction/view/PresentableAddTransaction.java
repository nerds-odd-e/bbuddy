package com.odde.bbuddy.transaction.view;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import static com.odde.bbuddy.transaction.domain.Transaction.Type;

@Component
public class PresentableAddTransaction {

    public void display(Model model, Type[] values) {
        model.addAttribute("types", values);
    }
}
