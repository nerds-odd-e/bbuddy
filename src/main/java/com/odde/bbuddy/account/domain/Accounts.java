package com.odde.bbuddy.account.domain;

import com.odde.bbuddy.account.repo.AccountRepo;
import com.odde.bbuddy.common.validator.FieldCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Accounts implements FieldCheck<String> {
    private final AccountRepo accountRepo;

    @Autowired
    public Accounts(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public AccountPostActions add(Account account) {
        try {
            accountRepo.save(account);
            return new SuccessAccountPostActions();
        } catch (IllegalArgumentException e) {
            return new FailedAccountPostActions();
        }
    }

    @Override
    public boolean isValueUnique(String value) {
        return !accountRepo.existsByName(value);
    }
}
