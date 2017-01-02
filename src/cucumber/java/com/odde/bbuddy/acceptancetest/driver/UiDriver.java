package com.odde.bbuddy.acceptancetest.driver;

import com.odde.bbuddy.common.view.Params;

public interface UiDriver {
    void close();

    void navigateTo(String url);

    UiElement findElementByName(String name);

    UiElement findElementByTag(String tag);

    void navigateToWithParams(String url, Params params);

    UiSelect findSelectByName(String name);

    UiElement findElementById(String id);

    void waitForTextPresent(String text);

    UiElement findLinkByText(String text);
}
