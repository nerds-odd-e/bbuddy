package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String confirm(@ModelAttribute Transaction transaction) {
        transactions.add(transaction);
        return "add_transaction";
    }
}
