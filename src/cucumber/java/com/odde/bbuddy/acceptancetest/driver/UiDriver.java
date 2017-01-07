package com.odde.bbuddy.acceptancetest.driver;

import com.odde.bbuddy.common.view.Params;

public interface UiDriver {
    void close();

    void navigateTo(String url);

    void navigateToWithParams(String url, Params params);

    void waitForTextPresent(String text);

    void inputTextByName(String text, String name);

    void clickByText(String text);

    void selectOptionByTextAndElementName(String text, String elementName);

    String getAllTextInPage();
}
