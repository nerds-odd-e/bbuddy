package com.odde.bbuddy.common.view;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticationFailedTest {

    SignIn signIn = new SignIn();

    @Test
    public void back_to_sign_in() {
        ModelAndView actual = signIn.getModel(null, null);

        assertThat(actual.getViewName()).isEqualTo("signin");
    }

    @Test
    public void message_when_sign_in_error() {
        signIn.failedMessage = "a failed message";

        ModelAndView actual = signIn.getModel("any error", null);

        assertThat(actual.getModel().get("message")).isEqualTo("a failed message");
        assertThat(actual.getModel().get("type")).isEqualTo("danger");
        assertThat(actual.getViewName()).isEqualTo("signin");
    }

    @Test
    public void message_when_logout() {
        signIn.logoutMessage = "a logout message";

        ModelAndView actual = signIn.getModel(null, "something logout");

        assertThat(actual.getModel().get("message")).isEqualTo("a logout message");
        assertThat(actual.getModel().get("type")).isEqualTo("info");
        assertThat(actual.getViewName()).isEqualTo("signin");
    }
}
