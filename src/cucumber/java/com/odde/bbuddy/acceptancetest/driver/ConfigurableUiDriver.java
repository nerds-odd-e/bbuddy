package com.odde.bbuddy.acceptancetest.driver;

import com.odde.bbuddy.common.view.Params;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class ConfigurableUiDriver implements UiDriver {

    private static final String DELIMITER = ":";
    private static final String HOST_NAME = "http://localhost";
    private final UiDriver originalDriver = new SeleniumWebDriver();
    @Value("${cucumber.port}")
    private String port;
    @Value("${cucumber.context-path}")
    private String contextPath;

    @Override
    public void close() {
        originalDriver.close();
    }

    @Override
    public void navigateTo(String url) {
        originalDriver.navigateTo(urlWithHostAndPort(url));
    }

    private String urlWithHostAndPort(String url) {
        return HOST_NAME + DELIMITER + port + contextPath + url;
    }

    @Override
    public void navigateToWithParams(String url, Params params) {
        originalDriver.navigateToWithParams(urlWithHostAndPort(url), params);
    }

    @Override
    public void waitForTextPresent(String text) {
        originalDriver.waitForTextPresent(text);
    }

    @Override
    public void inputTextByName(String text, String name) {
        originalDriver.inputTextByName(text, name);
    }

    @Override
    public void clickByText(String text) {
        originalDriver.clickByText(text);
    }

    @Override
    public void selectOptionByTextAndElementName(String text, String elementName) {
        originalDriver.selectOptionByTextAndElementName(text, elementName);
    }

    @Override
    public String getAllTextInPage() {
        return originalDriver.getAllTextInPage();
    }
}
