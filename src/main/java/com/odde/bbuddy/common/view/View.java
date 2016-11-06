package com.odde.bbuddy.common.view;

@FunctionalInterface
public interface View<T> {

    void display(T domainObject);

}
