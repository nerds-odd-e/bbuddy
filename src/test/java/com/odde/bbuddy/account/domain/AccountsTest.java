package com.odde.bbuddy.account.domain;

import com.odde.bbuddy.account.repo.AccountRepo;
import org.junit.Test;

import static com.odde.bbuddy.common.callback.PostActionsFactory.failed;
import static com.odde.bbuddy.common.callback.PostActionsFactory.success;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class AccountsTest {

    AccountRepo mockAccountRepo = mock(AccountRepo.class);
    Accounts accounts = new Accounts(mockAccountRepo);

    @Test
    public void should_save_account() {
        Account account = new Account();

        accounts.add(account);

        verify(mockAccountRepo).save(account);
    }
    
    @Test
    public void should_return_success_when_save_with_no_exception() {
        assertThat(accounts.add(new Account())).isInstanceOf(success().getClass());
    }

    @Test
    public void should_return_failed_when_save_with_exception() {
        given_account_repo_save_will_failed();

        assertThat(accounts.add(new Account())).isInstanceOf(failed().getClass());
    }

    private void given_account_repo_save_will_failed() {
        doThrow(IllegalArgumentException.class).when(mockAccountRepo).save(any(Account.class));
    }

}