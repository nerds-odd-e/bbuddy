package com.odde.bbuddy.common.view;

import com.odde.bbuddy.common.controller.SessionController;
import org.junit.Test;

import static com.odde.bbuddy.common.controller.Urls.SIGNIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SessionControllerTest {

    SignInView mockSignInView = mock(SignInView.class);
    SessionController controller = new SessionController(mockSignInView);

    @Test
    public void should_go_to_sign_in() {
        assertThat(signInFailed(controller)).isEqualTo(SIGNIN);
    }

    @Test
    public void should_display_view() {
        signInFailed(controller);

        verify(mockSignInView).display("error", "logout");
    }

    private String signInFailed(SessionController controller) {
        return controller.signIn("error", "logout");
    }

}
