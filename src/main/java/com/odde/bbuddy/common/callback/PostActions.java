package com.odde.bbuddy.common.callback;

public interface PostActions {
    PostActions success(Runnable afterSuccess);
    PostActions failed(Runnable afterFailed);
}
