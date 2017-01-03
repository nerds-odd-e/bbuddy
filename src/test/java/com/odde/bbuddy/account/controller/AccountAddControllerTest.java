package com.odde.bbuddy.account.controller;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.account.domain.Account;
import com.odde.bbuddy.account.domain.Accounts;
import com.odde.bbuddy.account.view.PresentableAddAccount;
import com.odde.bbuddy.common.callback.PostActions;
import com.odde.bbuddy.common.view.Result;
import com.odde.bbuddy.common.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.account.builder.AccountBuilder.defaultAccount;
import static com.odde.bbuddy.common.callback.PostActionsFactory.failed;
import static com.odde.bbuddy.common.callback.PostActionsFactory.success;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class AccountAddControllerTest {

    Accounts mockAccounts = mock(Accounts.class);
    View<String> mockView = mock(View.class);
    AccountAddController controller = new AccountAddController(mockAccounts, mockView, new PresentableAddAccount());
    Account account = defaultAccount().build();
    private final BindingResult stubBindingResult = mock(BindingResult.class);

    public class Add {

        @Test
        public void should_go_to_view() {
            assertThat(controller.addAccount()).isInstanceOf(PresentableAddAccount.class);
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
            assertThat(submitAddAccount()).isInstanceOf(PresentableAddAccount.class);
        }

        @Test
        public void should_add_account() {
            submitAddAccount();

            verify(mockAccounts).add(account);
        }

        @Test
        public void should_add_account_when_add_by_json() {
            submitAddAccountByJson();

            verify(mockAccounts).add(account);
        }
        
        public class Success {

            @Before
            public void given_add_account_will_success() {
                given_add_account_will(success());
            }

            @Test
            public void should_display_success_message() {
                submitAddAccount();

                verify(mockView).display("a success message");
                verify(mockView, never()).display("a failed message");
            }
            
            @Test
            public void should_return_success_status_and_message_when_add_by_json() {
                Result result = submitAddAccountByJson();

                assertThat(result.isSuccess()).isEqualTo(true);
                assertThat(result.getMessage()).isEqualTo("a success message");
            }

        }

        public class Failed {

            @Before
            public void given_add_account_will_failed() {
                given_add_account_will(failed());
            }

            @Test
            public void should_display_failed_message() {
                submitAddAccount();

                verify(mockView, never()).display("a success message");
                verify(mockView).display("a failed message");
            }
            
            @Test
            public void should_return_failed_status_and_message_when_add_by_json() {
                Result result = submitAddAccountByJson();

                assertThat(result.isSuccess()).isEqualTo(false);
                assertThat(result.getMessage()).isEqualTo("a failed message");
            }

        }

    }

    private Result submitAddAccountByJson() {
        return controller.submitAddAccountByJson(account);
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

    private ModelAndView submitAddAccount() {
        return controller.submitAddAccount(account, stubBindingResult);
    }

    private void given_add_account_will(PostActions postActions) {
        when(mockAccounts.add(account)).thenReturn(postActions);
    }

}