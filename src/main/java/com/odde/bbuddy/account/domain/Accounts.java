package com.odde.bbuddy.account.domain;

import com.odde.bbuddy.account.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Accounts {
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

}
