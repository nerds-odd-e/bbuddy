package com.odde.bbuddy.user.builder;

import com.odde.bbuddy.user.domain.AuthenticationResult;

import static com.odde.bbuddy.user.domain.AuthenticationResult.builder;

public class AuthenticationResultBuilder {

    public static AuthenticationResult.AuthenticationResultBuilder defaultAuthenticationResult() {
        return builder().error(null).logout(null);
    }
}
