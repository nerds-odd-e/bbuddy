package com.odde.bbuddy.user.builder;

import com.odde.bbuddy.user.view.SignInView;

public class SignInViewBuilder {

    public static SignInView.SignInViewBuilder defaultSignInView() {
        return SignInView.builder()
                .failedMessage("whatever message").logoutMessage("whatever message");
    }
}
