package com.odde.bbuddy.transaction.view;

import com.odde.bbuddy.transaction.domain.Transaction;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static com.odde.bbuddy.common.BeanUtils.copyProperties;

public class PresentableTransactions {
    private final List<PresentableTransaction> list = new ArrayList<>();
    private final Model model;
    private final String message;

    public PresentableTransactions(Model model, String message) {
        this.model = model;
        this.message = message;
        this.model.addAttribute("transactions", this);
    }

    public void add(Transaction transaction) {
        list.add(presentableTransactionFrom(transaction));
    }

    private PresentableTransaction presentableTransactionFrom(Transaction transaction) {
        PresentableTransaction pt = new PresentableTransaction();
        copyProperties(pt, transaction);
        return pt;
    }

    public List<PresentableTransaction> getList() {
        return list;
    }

    public String display() {
        return list.isEmpty() ? "hidden" : "";
    }

    public String message() {
        return list.isEmpty() ? message : "";
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
