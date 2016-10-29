package com.odde.bbuddy.account.domain;

public class FailedAccountPostActions implements AccountPostActions {

    @Override
    public AccountPostActions failed(Runnable afterFailed) {
        afterFailed.run();
        return this;
    }
}
