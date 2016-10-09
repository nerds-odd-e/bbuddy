package com.odde.bbuddy.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResult {

    private String error;
    private String logout;

    public AuthenticationResult error(Runnable afterError) {
        if (error != null)
            afterError.run();
        return this;
    }

    public AuthenticationResult logout(Runnable afterLogout) {
        if (logout != null)
            afterLogout.run();
        return this;
    }
}
