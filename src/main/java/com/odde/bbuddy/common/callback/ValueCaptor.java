package com.odde.bbuddy.common.callback;

public class ValueCaptor<T> {
    private T t;

    public void capture(T t) {
        this.t = t;
    }

    public T value() {
        return t;
    }
}
