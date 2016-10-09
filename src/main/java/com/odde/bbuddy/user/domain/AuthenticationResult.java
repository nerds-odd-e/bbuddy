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

    public boolean hasError() {
        return error != null;
    }

    public boolean isLogout() {
        return logout != null;
    }
}
