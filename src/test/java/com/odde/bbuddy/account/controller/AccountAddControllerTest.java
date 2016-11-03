package com.odde.bbuddy.account.controller;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.account.domain.Account;
import com.odde.bbuddy.account.domain.Accounts;
import com.odde.bbuddy.common.callback.PostActions;
import com.odde.bbuddy.common.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.validation.BindingResult;

import static com.odde.bbuddy.account.builder.AccountBuilder.defaultAccount;
import static com.odde.bbuddy.common.callback.PostActionsFactory.failed;
import static com.odde.bbuddy.common.callback.PostActionsFactory.success;
import static com.odde.bbuddy.common.controller.Urls.ACCOUNTS_ADD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class AccountAddControllerTest {

    Accounts mockAccounts = mock(Accounts.class);
    View<String> mockView = mock(View.class);
    AccountAddController controller = new AccountAddController(mockAccounts, mockView);
    Account account = defaultAccount().build();
    private final BindingResult stubBindingResult = mock(BindingResult.class);

    public class Add {

        @Test
        public void should_go_to_view() {
            assertThat(controller.addAccount()).isEqualTo(ACCOUNTS_ADD);
        }

    }

    public class SubmitAdd {

        @Before
        public void given_add_account_will_success() {
            given_add_account_will(success());
        }

        @Before
        public void given_messages() {
            controller.successMessage = "a success message";
            controller.failedMessage = "a failed message";
        }

        @Test
        public void should_go_to_view() {
            assertThat(submitAddAccount()).isEqualTo(ACCOUNTS_ADD);
        }

        @Test
        public void should_add_account() {
            submitAddAccount();

            verify(mockAccounts).add(account);
        }

        public class Success {

            @Test
            public void should_display_success_message() {
                given_add_account_will(success());

                submitAddAccount();

                verify(mockView).display("a success message");
                verify(mockView, never()).display("a failed message");
            }

        }

        public class Failed {

            @Test
            public void should_display_failed_message() {
                given_add_account_will(failed());

                submitAddAccount();

                verify(mockView, never()).display("a success message");
                verify(mockView).display("a failed message");
            }

        }

    }

    public class NameDuplicated {

        @Test
        public void should_not_add_account() {
            given_has_field_error();

            submitAddAccount();

            verify(mockAccounts, never()).add(account);
        }

        private void given_has_field_error() {
            when(stubBindingResult.hasErrors()).thenReturn(true);
        }

    }

    private String submitAddAccount() {
        return controller.submitAddAccount(account, stubBindingResult);
    }

    private void given_add_account_will(PostActions postActions) {
        when(mockAccounts.add(account)).thenReturn(postActions);
    }

}