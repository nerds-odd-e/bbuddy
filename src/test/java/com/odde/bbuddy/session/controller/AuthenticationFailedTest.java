package com.odde.bbuddy.session.controller;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticationFailedTest {

    SessionController controller = new SessionController();

    @Test
    public void back_to_sign_in() {
        ModelAndView actual = controller.signIn(null, null);

        assertThat(actual.getViewName()).isEqualTo("signin");
    }

    @Test
    public void message_when_sign_in_error() {
        ModelAndView actual = controller.signIn("any error", null);

        assertThat(actual.getModel().get("message")).isEqualTo("Invalid username and password!");
        assertThat(actual.getModel().get("type")).isEqualTo("danger");
        assertThat(actual.getViewName()).isEqualTo("signin");
    }

    @Test
    public void message_when_logout() {
        ModelAndView actual = controller.signIn(null, "something logout");

        assertThat(actual.getModel().get("message")).isEqualTo("You've been logged out successfully.");
        assertThat(actual.getModel().get("type")).isEqualTo("info");
        assertThat(actual.getViewName()).isEqualTo("signin");
    }
}
