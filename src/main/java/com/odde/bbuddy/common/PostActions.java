package com.odde.bbuddy.common;

public interface PostActions {
    PostActions success(Runnable afterSuccess);
    PostActions failed(Runnable afterFailed);
}
