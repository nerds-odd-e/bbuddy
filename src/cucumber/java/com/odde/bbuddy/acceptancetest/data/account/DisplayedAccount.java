package com.odde.bbuddy.acceptancetest.data.account;

import lombok.Setter;

import static org.assertj.core.api.Assertions.assertThat;

@Setter
public class DisplayedAccount {

    private String name;
    private String balanceBroughtForward;

    public void assertAllFieldsDisplayedIn(String text) {
        assertThat(text).contains(name, balanceBroughtForward);
    }
}
