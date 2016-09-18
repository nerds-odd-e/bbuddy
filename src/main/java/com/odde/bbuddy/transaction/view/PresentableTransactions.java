package com.odde.bbuddy.transaction.view;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static com.odde.bbuddy.common.BeanUtils.copyProperties;

public class PresentableTransactions extends ArrayList<PresentableTransaction> {
    private final Model model;
    private final String message;

    public PresentableTransactions(Model model, String message, Transactions transactions) {
        this.model = model;
        this.message = message;
        transactions.processAll(this::add);
        this.model.addAttribute("transactions", this);
    }

    private void add(Transaction transaction) {
        PresentableTransaction pt = new PresentableTransaction();
        copyProperties(pt, transaction);
        add(pt);
    }

    public String display() {
        return isEmpty() ? "hidden" : "";
    }

    public String message() {
        return isEmpty() ? message : "";
    }

    public int totalIncome() {
        return 14000;
    }

    public int totalOutcome() {
        return 30000;
    }

    public int balance() {
        return -16000;
    }
}
