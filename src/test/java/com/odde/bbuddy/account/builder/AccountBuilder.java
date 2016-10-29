package com.odde.bbuddy.account.builder;

import com.odde.bbuddy.account.domain.Account;

public class AccountBuilder {

    public static Account.AccountBuilder defaultAccount() {
        return Account.builder()
                .name("name")
                .balanceBroughtForward(0);
    }
}
