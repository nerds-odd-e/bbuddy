package com.odde.bbuddy.account.domain;

import com.odde.bbuddy.account.repo.AccountRepo;
import com.odde.bbuddy.common.callback.PostActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.odde.bbuddy.common.callback.PostActionsFactory.failed;
import static com.odde.bbuddy.common.callback.PostActionsFactory.success;

@Service
public class Accounts {
    private final AccountRepo accountRepo;

    @Autowired
    public Accounts(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public PostActions add(Account account) {
        try {
            accountRepo.save(account);
            return success();
        } catch (IllegalArgumentException e) {
            return failed();
        }
    }
}
