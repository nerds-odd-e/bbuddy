package com.odde.bbuddy.acceptancetest.driver;

public interface UiDriver {
    void close();

    void navigateTo(String url);

    UiElement findElementByName(String name);

    UiElement findElementByTag(String tag);
}
