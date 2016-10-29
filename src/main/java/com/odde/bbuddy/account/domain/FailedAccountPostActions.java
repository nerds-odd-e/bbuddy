package com.odde.bbuddy.account.domain;

public class FailedAccountPostActions implements AccountPostActions {
    @Override
    public AccountPostActions success(Runnable afterSuccess) {
        return this;
    }

    @Override
    public AccountPostActions failed(Runnable afterFailed) {
        afterFailed.run();
        return this;
    }

    @Override
    public AccountPostActions nameDuplicated(Runnable afterNameDuplicated) {
        return this;
    }
}
