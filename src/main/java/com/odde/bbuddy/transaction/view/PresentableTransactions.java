package com.odde.bbuddy.transaction.view;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.odde.bbuddy.common.BeanUtils.copyProperties;
import static com.odde.bbuddy.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class PresentableTransactions extends ArrayList<PresentableTransaction> {

    private final Transactions transactions;
    private final HttpServletRequest request;

    @Value("${transaction.list.empty}")
    String noTransactionMessage;

    @Autowired
    public PresentableTransactions(Transactions transactions, HttpServletRequest request) {
        this.transactions = transactions;
        this.request = request;
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
        request.setAttribute("transactions", this);
    }
}
