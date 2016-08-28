package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TransactionController {

    private final Transactions transactions;

    @Autowired
    public TransactionController(Transactions transactions) {
        this.transactions = transactions;
    }

    @RequestMapping(value = "/confirm_add_transaction", method = RequestMethod.POST)
    public String addTransaction(@ModelAttribute Transaction transaction, Model model) {
        transactions.add(transaction,
                setMessage(model, "Successfully add transaction"),
                setMessage(model, "Add transaction failed"));
        return "add_transaction";
    }

    private Runnable setMessage(Model model, String message) {
        return () -> model.addAttribute("message", message);
    }
}
