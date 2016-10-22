package com.odde.bbuddy.common.builder;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.function.Consumer;

public class ConsumeAnswer<T> implements Answer<T> {

    private final T t;
    private final Object consumed;

    public ConsumeAnswer(Object consumed, T t) {
        this.t = t;
        this.consumed = consumed;
    }

    public ConsumeAnswer(Object consumed) {
        this(consumed, null);
    }

    @Override
    public T answer(InvocationOnMock invocation) throws Throwable {
        consumer(invocation).accept(consumed);
        return t;
    }

    private Consumer consumer(InvocationOnMock invocation) {
        return invocation.getArgumentAt(0, Consumer.class);
    }
}
