package com.odde.bbuddy.account.domain;

public class NameDuplicatedAccountPostActions implements AccountPostActions {

    @Override
    public AccountPostActions nameDuplicated(Runnable afterNameDuplicated) {
        afterNameDuplicated.run();
        return this;
    }
}
