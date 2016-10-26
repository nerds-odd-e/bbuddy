package com.odde.bbuddy.account.domain;

import com.odde.bbuddy.account.repo.AccountRepo;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AccountsTest {

    @Test
    public void should_save_account() {
        AccountRepo mockAccountRepo = mock(AccountRepo.class);
        Accounts accounts = new Accounts(mockAccountRepo);

        Account account = new Account();
        accounts.add(account);

        verify(mockAccountRepo).save(account);
    }
    
}