package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import com.odde.bbuddy.transaction.view.PresentableTransactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import static com.odde.bbuddy.common.controller.ControllerHelper.thenSetMessage;
import static com.odde.bbuddy.common.controller.Urls.TRANSACTION_ADD;
import static com.odde.bbuddy.common.controller.Urls.TRANSACTION_LIST;
import static com.odde.bbuddy.transaction.domain.Transaction.Type.values;

@Controller
@PropertySource("classpath:resultMessages.properties")
public class TransactionController {

    private final Transactions transactions;

    @Value("${transaction.add.success}")
    String successMessage;

    @Value("${transaction.add.failed}")
    String failedMessage;

    @Value("${transaction.list.empty}")
    String noTransactionMessage;

    @Autowired
    public TransactionController(Transactions transactions) {
        this.transactions = transactions;
    }

    @RequestMapping(value = TRANSACTION_ADD, method = RequestMethod.POST)
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

    @RequestMapping(value = TRANSACTION_ADD, method = RequestMethod.GET)
    public String addTransaction(Model model) {
        model.addAttribute("types", values());
        return TRANSACTION_ADD;
    }

    @RequestMapping(value = TRANSACTION_LIST, method = RequestMethod.GET)
    public String showTransactions(Model model) {
        PresentableTransactions pts = new PresentableTransactions(model, noTransactionMessage);
        transactions.processAll(transaction -> pts.add(transaction));

        return TRANSACTION_LIST;
    }

}
