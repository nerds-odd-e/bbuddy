package com.odde.bbuddy.account.controller;

import com.odde.bbuddy.account.domain.Account;
import com.odde.bbuddy.account.domain.Accounts;
import com.odde.bbuddy.common.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.odde.bbuddy.common.controller.Urls.*;

@Controller
@RequestMapping(ACCOUNTS)
public class AccountController {

    private final Accounts accounts;
    private final View<String> message;

    @Value("${accounts.add.success}")
    String successMessage;

    @Value("${accounts.add.failed}")
    String failedMessage;

    @Autowired
    public AccountController(Accounts accounts, View<String> message) {
        this.accounts = accounts;
        this.message = message;
    }

    @GetMapping(ADD)
    public String addAccount() {
        return ACCOUNTS_ADD;
    }

    @PostMapping(ADD)
    public String submitAddAccount(@Valid @ModelAttribute Account account, BindingResult bindingResult) {
        if (!bindingResult.hasErrors())
            accounts.add(account)
                    .success(() -> message.display(successMessage))
                    .failed(() -> message.display(failedMessage));

        return ACCOUNTS_ADD;
    }
}
