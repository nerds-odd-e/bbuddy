package com.odde.bbuddy.account.domain;

import com.odde.bbuddy.account.repo.AccountRepo;
import com.odde.bbuddy.common.callback.PostActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Accounts {
    private final AccountRepo accountRepo;

    @Autowired
    public Accounts(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public PostActions add(Account account) {
        accountRepo.save(account);
        return null;
    }
}
