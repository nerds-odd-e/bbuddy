package com.odde.bbuddy.common;

public class SuccessPostActions implements PostActions {
    @Override
    public PostActions success(Runnable afterSuccess) {
        afterSuccess.run();
        return this;
    }

    @Override
    public PostActions failed(Runnable afterFailed) {
        return this;
    }
}
