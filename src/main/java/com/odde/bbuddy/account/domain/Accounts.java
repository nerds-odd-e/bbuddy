package com.odde.bbuddy.account.domain;

import com.odde.bbuddy.account.repo.Account;
import com.odde.bbuddy.account.repo.AccountRepo;
import com.odde.bbuddy.common.callback.PostActions;
import com.odde.bbuddy.common.validator.FieldCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.odde.bbuddy.common.callback.PostActionsFactory.failed;
import static com.odde.bbuddy.common.callback.PostActionsFactory.success;

@Service
public class Accounts implements FieldCheck<String> {
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

    @Override
    public boolean isValueUnique(String value) {
        return !accountRepo.existsByName(value);
    }
}
