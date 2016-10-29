package com.odde.bbuddy.account.controller;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.account.domain.*;
import com.odde.bbuddy.common.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.odde.bbuddy.common.controller.Urls.ACCOUNTS_ADD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class AccountControllerTest {

    Accounts mockAccounts = mock(Accounts.class);
    View<String> mockView = mock(View.class);
    AccountController controller = new AccountController(mockAccounts, mockView);
    Account account = new Account();

    public class Add {

        @Test
        public void should_go_to_view() {
            assertThat(controller.addAccount()).isEqualTo(ACCOUNTS_ADD);
        }

    }

    public class SubmitAdd {

        @Before
        public void given_add_account_will_success() {
            given_add_account_will(new SuccessAccountPostActions());
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
                given_add_account_will(new SuccessAccountPostActions());

                submitAddAccount();

                verify(mockView).display("a success message");
                verify(mockView, never()).display("a failed message");
            }

        }

        public class Failed {

            @Test
            public void should_display_failed_message() {
                given_add_account_will(new FailedAccountPostActions());

                submitAddAccount();

                verify(mockView, never()).display("a success message");
                verify(mockView).display("a failed message");
            }

        }

        private String submitAddAccount() {
            return controller.submitAddAccount(account);
        }

    }

    public class Valid {

        @Test
        public void account_name_can_not_duplicate() {
            given_add_account_will(new NameDuplicatedAccountPostActions());
            controller.nameDuplicatedMessage = "a name duplicated message";

            controller.submitAddAccount(account);

            verify(mockView).display("a name duplicated message");
        }

    }

    private void given_add_account_will(AccountPostActions postActions) {
        when(mockAccounts.add(account)).thenReturn(postActions);
    }

}