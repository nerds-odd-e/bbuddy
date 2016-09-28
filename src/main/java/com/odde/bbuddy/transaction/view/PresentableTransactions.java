package com.odde.bbuddy.transaction.view;

import com.odde.bbuddy.common.view.Model;
import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.odde.bbuddy.common.BeanUtils.copyProperties;
import static com.odde.bbuddy.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;

@Component
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class PresentableTransactions extends ArrayList<PresentableTransaction> {

    private final Transactions transactions;
    private final Model model;

    @Value("${transaction.list.empty}")
    String noTransactionMessage;

    @Autowired
    public PresentableTransactions(Transactions transactions, Model model) {
        this.transactions = transactions;
        this.model = model;
    }

    private void add(Transaction transaction) {
        PresentableTransaction pt = new PresentableTransaction();
        copyProperties(pt, transaction);
        add(pt);
    }

    public String hidden() {
        return isEmpty() ? "hidden" : "";
    }

    public String message() {
        return isEmpty() ? noTransactionMessage : "";
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

    public void display() {
        transactions.processAll(this::add);
        model.addAttribute("transactions", this);
    }
}
