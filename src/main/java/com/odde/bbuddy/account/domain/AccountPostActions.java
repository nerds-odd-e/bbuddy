package com.odde.bbuddy.account.domain;

public interface AccountPostActions {

    default AccountPostActions success(Runnable afterSuccess) {
        return this;
    }

    default AccountPostActions failed(Runnable afterFailed) {
        return this;
    }

    default AccountPostActions nameDuplicated(Runnable afterNameDuplicated) {
        return this;
    }

}
