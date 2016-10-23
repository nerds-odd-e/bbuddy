package com.odde.bbuddy.user.controller;

import com.odde.bbuddy.user.domain.AuthenticationResult;
import com.odde.bbuddy.user.view.SignInView;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.user.builder.AuthenticationResultBuilder.defaultAuthenticationResult;
import static com.odde.bbuddy.user.builder.SignInViewBuilder.defaultSignInView;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class SessionControllerTest {

    AuthenticationResult authenticationResult = defaultAuthenticationResult().build();
    SignInView signInView = spy(defaultSignInView().build());
    SessionController controller = new SessionController(signInView);

    @Test
    public void should_display_view() {
        assertThat(signIn()).isInstanceOf(SignInView.class);
    }

    @Test
    public void should_pass_error_and_logout_to_view() {
        signIn();

        verify(signInView).display(authenticationResult);
    }

    private ModelAndView signIn() {
        return controller.signIn(authenticationResult);
    }

}
