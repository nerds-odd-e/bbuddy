package com.odde.bbuddy.common.callback;

public class SuccessPostActions implements PostActions {
    @Override
    public PostActions success(Runnable afterSuccess) {
        afterSuccess.run();
        return this;
    }

}
