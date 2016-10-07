package com.odde.bbuddy.common.builder;

import com.odde.bbuddy.common.view.SignInView;

public class SignInViewBuilder {

    public static SignInView.SignInViewBuilder defaultSignInView() {
        return SignInView.builder()
                .failedMessage("whatever message").logoutMessage("whatever message");
    }
}
