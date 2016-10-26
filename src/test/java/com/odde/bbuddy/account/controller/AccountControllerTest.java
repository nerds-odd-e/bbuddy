package com.odde.bbuddy.account.controller;

import com.nitorcreations.junit.runners.NestedRunner;
import com.odde.bbuddy.account.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.odde.bbuddy.common.controller.Urls.ACCOUNT_ADD;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(NestedRunner.class)
public class AccountControllerTest {

    AccountController controller = new AccountController();

    public class Add {

        @Test
        public void should_go_to_view() {
            assertThat(controller.addAccount()).isEqualTo(ACCOUNT_ADD);
        }

    }

    public class AddAccountSuccess {
        
        @Test
        public void should_go_to_view() {
            assertThat(controller.submitAddAccount(new Account())).isEqualTo(ACCOUNT_ADD);
        }
    }

}