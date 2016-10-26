package com.odde.bbuddy.account.controller;

import com.odde.bbuddy.account.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.odde.bbuddy.common.controller.Urls.ACCOUNT;
import static com.odde.bbuddy.common.controller.Urls.ACCOUNT_ADD;
import static com.odde.bbuddy.common.controller.Urls.ADD;

@Controller
@RequestMapping(ACCOUNT)
public class AccountController {

    @GetMapping(ADD)
    public String addAccount() {
        return ACCOUNT_ADD;
    }

    @PostMapping(ADD)
    public String submitAddAccount(Account account) {
        return ACCOUNT_ADD;
    }
}
