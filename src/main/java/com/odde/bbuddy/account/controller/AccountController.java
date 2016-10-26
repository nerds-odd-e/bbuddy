package com.odde.bbuddy.account.controller;

import com.odde.bbuddy.account.domain.Account;
import com.odde.bbuddy.account.domain.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.odde.bbuddy.common.controller.Urls.ACCOUNT;
import static com.odde.bbuddy.common.controller.Urls.ACCOUNT_ADD;
import static com.odde.bbuddy.common.controller.Urls.ADD;

@Controller
@RequestMapping(ACCOUNT)
public class AccountController {

    private final Accounts accounts;

    @Autowired
    public AccountController(Accounts accounts) {
        this.accounts = accounts;
    }

    @GetMapping(ADD)
    public String addAccount() {
        return ACCOUNT_ADD;
    }

    @PostMapping(ADD)
    public String submitAddAccount(@ModelAttribute Account account) {
        accounts.add(account);

        return ACCOUNT_ADD;
    }
}
