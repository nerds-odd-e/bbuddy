package com.odde.bbuddy.common.view;

import com.odde.bbuddy.common.controller.SessionController;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionControllerTest {

    SessionController controller = new SessionController(new SignInView());

    @Test
    public void should_display_view() {
        assertThat(controller.signIn("error", "logout")).isInstanceOf(SignInView.class);
    }

}
