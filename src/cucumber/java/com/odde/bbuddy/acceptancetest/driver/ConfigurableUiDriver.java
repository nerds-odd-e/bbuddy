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
    public UiElement findElementByName(String name) {
        return originalDriver.findElementByName(name);
    }

    @Override
    public UiElement findElementByTag(String tag) {
        return originalDriver.findElementByTag(tag);
    }

    @Override
    public void navigateToWithParams(String url, Params params) {
        originalDriver.navigateToWithParams(urlWithHostAndPort(url), params);
    }

    @Override
    public UiSelect findSelectByName(String name) {
        return originalDriver.findSelectByName(name);
    }

    @Override
    public UiElement findElementById(String id) {
        return originalDriver.findElementById(id);
    }

    @Override
    public void waitForTextPresent(String text) {
        originalDriver.waitForTextPresent(text);
    }

    @Override
    public UiElement findLinkByText(String text) {
        return originalDriver.findLinkByText(text);
    }
}
