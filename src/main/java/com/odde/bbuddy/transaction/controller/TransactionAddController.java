package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.common.view.View;
import com.odde.bbuddy.transaction.domain.Transactions;
import com.odde.bbuddy.transaction.repo.Transaction;
import com.odde.bbuddy.transaction.view.PresentableAddTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static com.odde.bbuddy.common.controller.Urls.ADD;
import static com.odde.bbuddy.common.controller.Urls.TRANSACTIONS;
import static com.odde.bbuddy.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;

@Controller
@PropertySource(RESULT_MESSAGES_FULL_NAME)
@RequestMapping(TRANSACTIONS)
public class TransactionAddController {

    private final Transactions transactions;
    private final PresentableAddTransaction presentableAddTransaction;

    private final View<String> message;

    @Value("${transactions.add.success}")
    String successMessage;

    @Value("${transactions.add.failed}")
    String failedMessage;

    @Autowired
    public TransactionAddController(
            Transactions transactions,
            PresentableAddTransaction presentableAddTransaction,
            View<String> message) {
        this.transactions = transactions;
        this.presentableAddTransaction = presentableAddTransaction;
        this.message = message;
    }

    @PostMapping(ADD)
    public ModelAndView submitAddTransaction(
            @Valid @ModelAttribute Transaction transaction,
            BindingResult result) {
        if (!result.hasFieldErrors())
            transactions.add(transaction)
                    .success(() -> message.display(successMessage))
                    .failed(() -> message.display(failedMessage));
        return addTransaction();
    }

    @GetMapping(ADD)
    public ModelAndView addTransaction() {
        return presentableAddTransaction;
    }

}
