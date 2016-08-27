package com.odde.bbuddy.transaction.controller;

import com.odde.bbuddy.transaction.domain.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TransactionController {

    @RequestMapping(value = "/confirm_add_transaction", method = RequestMethod.POST)
    public String confirm(@ModelAttribute Transaction transaction) {
        return "add_transaction";
    }
}
