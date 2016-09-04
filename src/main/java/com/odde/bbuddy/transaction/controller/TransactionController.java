package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.odde.bbuddy.Urls.TRANSACTION_ADD;
import static com.odde.bbuddy.Urls.TRANSACTION_LIST;
import static com.odde.bbuddy.common.controller.ControllerHelper.setMessage;

@Controller
public class TransactionController {

    private final Transactions transactions;

    @Autowired
    public TransactionController(Transactions transactions) {
        this.transactions = transactions;
    }

    @RequestMapping(value = TRANSACTION_ADD, method = RequestMethod.POST)
    public String submitAddTransaction(@ModelAttribute Transaction transaction, Model model) {
        transactions.add(transaction)
                .success(setMessage(model, "Successfully add transaction"))
                .failed(setMessage(model, "Add transaction failed"));
        return addTransaction(model);
    }

    @RequestMapping(value = TRANSACTION_ADD, method = RequestMethod.GET)
    public String addTransaction(Model model) {
        model.addAttribute("types", Transaction.Type.values());
        return TRANSACTION_ADD;
    }

    @RequestMapping(value = TRANSACTION_LIST, method = RequestMethod.GET)
    public String showTransactions() {
        return TRANSACTION_LIST;
    }
}
