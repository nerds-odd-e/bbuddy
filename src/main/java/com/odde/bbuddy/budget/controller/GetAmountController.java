package com.odde.bbuddy.budget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GetAmountController {

    @RequestMapping("/get_amount")
    public String getAmount() {
        return "get_amount";
    }

}
