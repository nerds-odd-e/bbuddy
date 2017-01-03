package com.odde.bbuddy.account.controller;

import com.odde.bbuddy.account.domain.Account;
import com.odde.bbuddy.account.domain.Accounts;
import com.odde.bbuddy.account.view.PresentableAddAccount;
import com.odde.bbuddy.common.callback.ValueCaptor;
import com.odde.bbuddy.common.view.Result;
import com.odde.bbuddy.common.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static com.odde.bbuddy.common.controller.Urls.*;

@Controller
@RequestMapping(ACCOUNTS)
public class AccountAddController {

    private final Accounts accounts;
    private final View<String> message;
    private final PresentableAddAccount presentableAddAccount;

    @Value("${accounts.add.success}")
    String successMessage;

    @Value("${accounts.add.failed}")
    String failedMessage;

    @Autowired
    public AccountAddController(
            Accounts accounts,
            View<String> message,
            PresentableAddAccount presentableAddAccount) {
        this.accounts = accounts;
        this.message = message;
        this.presentableAddAccount = presentableAddAccount;
    }

    @GetMapping(ADD)
    public ModelAndView addAccount() {
        return presentableAddAccount;
    }

    @PostMapping(ADD)
    public ModelAndView submitAddAccount(@Valid @ModelAttribute Account account, BindingResult bindingResult) {
        if (!bindingResult.hasErrors())
            accounts.add(account)
                    .success(() -> message.display(successMessage))
                    .failed(() -> message.display(failedMessage));

        return addAccount();
    }

    @PostMapping(ADD + ".json")
    public @ResponseBody Result submitAddAccountByJson(@Valid @RequestBody Account account) {
        ValueCaptor<Result> captor = new ValueCaptor<>();

        accounts.add(account)
                .success(() -> captor.capture(Result.success(successMessage)))
                .failed(() -> captor.capture(Result.failed(failedMessage)));
        
        return captor.value();
    }

}
