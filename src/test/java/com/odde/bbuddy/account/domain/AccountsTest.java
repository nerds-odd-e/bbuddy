package com.odde.bbuddy.account.domain;

import com.odde.bbuddy.account.repo.AccountRepo;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class AccountsTest {

    AccountRepo mockAccountRepo = mock(AccountRepo.class);
    Accounts accounts = new Accounts(mockAccountRepo);
    Account account = new Account();

    Runnable afterSuccess = mock(Runnable.class);
    Runnable afterFailed = mock(Runnable.class);

    @Test
    public void should_save_account() {
        accounts.add(account);

        verify(mockAccountRepo).save(account);
    }
    
    @Test
    public void should_return_success_when_save_with_no_exception() {
        accounts.add(account)
                .success(afterSuccess)
                .failed(afterFailed);

        verify(afterSuccess).run();
        verify(afterFailed, never()).run();
    }

    @Test
    public void should_return_failed_when_save_with_exception() {
        given_account_repo_save_will_failed();

        accounts.add(account)
                .success(afterSuccess)
                .failed(afterFailed);

        verify(afterSuccess, never()).run();
        verify(afterFailed).run();
    }

    private void given_account_repo_save_will_failed() {
        doThrow(IllegalArgumentException.class).when(mockAccountRepo).save(any(Account.class));
    }

}