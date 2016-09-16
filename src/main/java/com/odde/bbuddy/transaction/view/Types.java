package com.odde.bbuddy.transaction.view;

import org.springframework.ui.Model;

import static com.odde.bbuddy.transaction.domain.Transaction.Type;

public class Types {
    public Types(Model model, Type[] types) {
        model.addAttribute("types", types);
    }
}
