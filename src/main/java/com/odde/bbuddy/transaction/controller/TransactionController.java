package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import com.odde.bbuddy.transaction.view.PresentableAddTransaction;
import com.odde.bbuddy.transaction.view.PresentableTransactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.odde.bbuddy.common.controller.ControllerHelper.thenSetMessage;
import static com.odde.bbuddy.common.controller.Urls.*;
import static com.odde.bbuddy.transaction.domain.Transaction.Type.*;

@Controller
@PropertySource("classpath:resultMessages.properties")
@RequestMapping(TRANSACTION)
public class TransactionController {

    private final Transactions transactions;
    private final PresentableAddTransaction presentableAddTransaction;

    @Value("${transaction.add.success}")
    String successMessage;

    @Value("${transaction.add.failed}")
    String failedMessage;

    @Value("${transaction.list.empty}")
    String noTransactionMessage;

    @Autowired
    public TransactionController(Transactions transactions, PresentableAddTransaction presentableAddTransaction) {
        this.transactions = transactions;
        this.presentableAddTransaction = presentableAddTransaction;
    }

    @PostMapping(ADD)
    public String submitAddTransaction(
            @Valid @ModelAttribute Transaction transaction,
            BindingResult result,
            Model model) {
        if (!result.hasFieldErrors())
            transactions.add(transaction)
                    .success(thenSetMessage(model, successMessage))
                    .failed(thenSetMessage(model, failedMessage));
        return addTransaction(model);
    }

    @GetMapping(ADD)
    public String addTransaction(Model model) {
        presentableAddTransaction.display(model, values());
        return TRANSACTION_ADD;
    }

    @GetMapping
    public String index(Model model) {
        new PresentableTransactions(model, noTransactionMessage, transactions);

        return TRANSACTION_INDEX;
    }

}
