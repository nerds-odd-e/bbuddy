package com.odde.bbuddy.account.controller;

import com.odde.bbuddy.account.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.odde.bbuddy.common.controller.Urls.ACCOUNTS;
import static com.odde.bbuddy.common.controller.Urls.ACCOUNTS_INDEX;

@Controller
@RequestMapping(ACCOUNTS)
public class AccountListController {

    @GetMapping
    public String index() {
        return ACCOUNTS_INDEX;
    }

    @GetMapping("list.json")
    @ResponseBody
    public List<Account> list() {
        return Arrays.asList(new Account(0, "Jackson", 100));
    }

}
