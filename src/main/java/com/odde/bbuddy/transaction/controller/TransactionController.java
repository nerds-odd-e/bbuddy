package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import com.odde.bbuddy.transaction.view.PresentableTransaction;
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
import java.util.ArrayList;
import java.util.List;

import static com.odde.bbuddy.common.BeanUtils.copyProperties;
import static com.odde.bbuddy.common.controller.ControllerHelper.setMessage;
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
                    .success(setMessage(model, successMessage))
                    .failed(setMessage(model, failedMessage));
        return addTransaction(model);
    }

    @RequestMapping(value = TRANSACTION_ADD, method = RequestMethod.GET)
    public String addTransaction(Model model) {
        model.addAttribute("types", values());
        return TRANSACTION_ADD;
    }

    @RequestMapping(value = TRANSACTION_LIST, method = RequestMethod.GET)
    public String showTransactions(Model model) {
        model.addAttribute("transactions", allTransactions());

        return TRANSACTION_LIST;
    }

    private List<PresentableTransaction> allTransactions() {
        List<PresentableTransaction> all = new ArrayList<>();
        transactions.processAll(transaction -> all.add(presentableTransactionFrom(transaction)));
        return all;
    }

    private PresentableTransaction presentableTransactionFrom(Transaction transaction) {
        PresentableTransaction pt = new PresentableTransaction();
        copyProperties(pt, transaction);
        return pt;
    }

}
