package com.odde.bbuddy.common.validator;

@FunctionalInterface
public interface FieldCheck<T> {

    boolean isValueUnique(T t);
}
