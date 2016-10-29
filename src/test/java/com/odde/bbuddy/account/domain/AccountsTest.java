package com.odde.bbuddy.account.domain;

import com.odde.bbuddy.account.repo.AccountRepo;
import org.junit.Test;

import static com.odde.bbuddy.account.builder.AccountBuilder.defaultAccount;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class AccountsTest {

    AccountRepo mockAccountRepo = mock(AccountRepo.class);
    Accounts accounts = new Accounts(mockAccountRepo);
    Account account = defaultAccount().build();

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
    
    @Test
    public void should_return_name_duplicated_when_save_with_a_duplicated_name_account() {
        given_account_name_exists("account name");
        Runnable afterNameDuplicated = mock(Runnable.class);

        accounts.add(defaultAccount().name("account name").build())
                .nameDuplicated(afterNameDuplicated);

        verify(afterNameDuplicated).run();
    }

    private void given_account_name_exists(String name) {
        when(mockAccountRepo.existsByName(name)).thenReturn(true);
    }

    private void given_account_repo_save_will_failed() {
        doThrow(IllegalArgumentException.class).when(mockAccountRepo).save(any(Account.class));
    }

}