package com.odde.bbuddy.transaction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TransactionController {

    @RequestMapping("/confirm_add_transaction")
    public String confirm() {
        return "add_transaction";
    }
}
