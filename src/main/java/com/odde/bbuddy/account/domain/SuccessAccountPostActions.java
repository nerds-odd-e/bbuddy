package com.odde.bbuddy.account.domain;

public class SuccessAccountPostActions implements AccountPostActions {

    @Override
    public AccountPostActions success(Runnable afterSuccess) {
        afterSuccess.run();
        return this;
    }
}
