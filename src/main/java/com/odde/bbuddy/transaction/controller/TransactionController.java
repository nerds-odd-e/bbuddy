package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.domain.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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
    public String submitAddTransaction(
            @Valid @ModelAttribute Transaction transaction,
            BindingResult result,
            Model model) {
        if (!result.hasFieldErrors())
            transactions.add(transaction)
                    .success(setMessage(model, "Successfully add transaction"))
                    .failed(setMessage(model, "Add transaction failed"));
        else
            setErrorMessages(result, model);
        return addTransaction(model);
    }

    private void setErrorMessages(BindingResult result, Model model) {
        result.getFieldErrors()
                .forEach(fieldError -> setErrorMessage(model, fieldError));
    }

    private Model setErrorMessage(Model model, FieldError fieldError) {
        return model.addAttribute("error." + fieldError.getField(), fieldError.getDefaultMessage());
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
