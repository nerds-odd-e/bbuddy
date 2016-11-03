package com.odde.bbuddy.account.view;

import lombok.Setter;

@Setter
public class PresentableAccount {

    private String name;
    private String balanceBroughtForward;

    public String[] allViewText() {
        return new String[]{name, balanceBroughtForward};
    }
}
