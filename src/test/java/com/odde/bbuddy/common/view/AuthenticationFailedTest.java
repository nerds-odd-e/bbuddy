package com.odde.bbuddy.common.view;

import com.odde.bbuddy.common.controller.SessionController;
import org.junit.Test;
import org.springframework.ui.Model;

import static com.odde.bbuddy.common.controller.Urls.SIGNIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AuthenticationFailedTest {

    SignIn signIn = new SignIn();
    Model mockModel = mock(Model.class);

    @Test
    public void back_to_sign_in() {
        SessionController controller = new SessionController(signIn);

        assertThat(controller.signIn(null, null, mockModel)).isEqualTo(SIGNIN);
    }

    @Test
    public void message_when_sign_in_error() {
        signIn.failedMessage = "a failed message";

        signIn.display("any error", null, mockModel);

        verify(mockModel).addAttribute("message", "a failed message");
        verify(mockModel).addAttribute("type", "danger");
    }

    @Test
    public void message_when_logout() {
        signIn.logoutMessage = "a logout message";

        signIn.display(null, "something logout", mockModel);

        verify(mockModel).addAttribute("message", "a logout message");
        verify(mockModel).addAttribute("type", "info");
    }
}
