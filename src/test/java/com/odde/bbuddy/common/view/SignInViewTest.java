package com.odde.bbuddy.common.view;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SignInViewTest {

    Model mockModel = mock(Model.class);
    SignInView view = new SignInView(mockModel);

    @Test
    public void message_when_sign_in_error() {
        view.failedMessage = "a failed message";

        view.display("any error", null);

        verify(mockModel).addAttribute("message", "a failed message");
        verify(mockModel).addAttribute("type", "danger");
    }

    @Test
    public void message_when_logout() {
        view.logoutMessage = "a logout message";

        view.display(null, "something logout");

        verify(mockModel).addAttribute("message", "a logout message");
        verify(mockModel).addAttribute("type", "info");
    }
}
