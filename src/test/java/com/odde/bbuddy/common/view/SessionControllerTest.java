package com.odde.bbuddy.common.view;

import com.odde.bbuddy.common.controller.SessionController;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SessionControllerTest {

    @Test
    public void should_display_view() {
        SessionController controller = new SessionController(new SignInView("whatever failed message", "whatever logout message"));

        assertThat(controller.signIn("error", "logout")).isInstanceOf(SignInView.class);
    }

    @Test
    public void should_pass_error_and_logout_to_view() {
        SignInView mockSignInView = mock(SignInView.class);
        SessionController controller = new SessionController(mockSignInView);

        controller.signIn("error", "logout");

        verify(mockSignInView).display("error", "logout");
    }

}
