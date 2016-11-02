package com.odde.bbuddy.common.validator;

public interface FieldCheck<T> {

    boolean isValueUnique(T t);
}
