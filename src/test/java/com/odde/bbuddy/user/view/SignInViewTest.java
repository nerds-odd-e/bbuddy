package com.odde.bbuddy.user.view;

import org.junit.Test;

import static com.odde.bbuddy.user.builder.AuthenticationResultBuilder.defaultAuthenticationResult;
import static com.odde.bbuddy.user.builder.SignInViewBuilder.defaultSignInView;
import static org.assertj.core.api.Assertions.assertThat;

public class SignInViewTest {

    SignInView view;

    @Test
    public void message_when_sign_in_error() {
        view = defaultSignInView().failedMessage("a failed message").build();

        view.display(defaultAuthenticationResult().error("any error").build());

        assertMessageEquals("a failed message");
        assertTypeEquals("danger");
    }

    @Test
    public void message_when_logout() {
        view = defaultSignInView().logoutMessage("a logout message").build();

        view.display(defaultAuthenticationResult().logout("something logout").build());

        assertMessageEquals("a logout message");
        assertTypeEquals("info");
    }

    private void assertTypeEquals(String danger) {
        assertThat(view.getModel().get("type")).isEqualTo(danger);
    }

    private void assertMessageEquals(String expected) {
        assertThat(view.getModel().get("message")).isEqualTo(expected);
    }

}
