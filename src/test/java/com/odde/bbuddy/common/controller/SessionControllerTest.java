package com.odde.bbuddy.common.controller;

import com.odde.bbuddy.common.view.SignInView;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class SessionControllerTest {

    SignInView signInView = spy(new SignInView("whatever failed message", "whatever logout message"));
    SessionController controller = new SessionController(signInView);

    @Test
    public void should_display_view() {
        assertThat(signIn()).isInstanceOf(SignInView.class);
    }

    @Test
    public void should_pass_error_and_logout_to_view() {
        spyOnDisplayOfSignInView();

        signIn();

        verify(signInView).display("error", "logout");
    }

    private void spyOnDisplayOfSignInView() {
        doReturn(signInView).when(signInView).display(anyString(), anyString());
    }

    private ModelAndView signIn() {
        return controller.signIn("error", "logout");
    }

}
