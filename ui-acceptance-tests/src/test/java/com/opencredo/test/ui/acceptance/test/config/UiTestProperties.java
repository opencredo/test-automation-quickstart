package com.opencredo.test.ui.acceptance.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Used to retrieve properties and make them available to tests
 */
public class UiTestProperties {
    @Autowired
    private Environment environment;

    public String getApplicationBaseUrl() {
        return environment.getProperty("ui.acceptance.test.application.base.url");
    }

    public int getSeleniumWaitTimeOutSeconds() {
        return environment.getProperty("ui.acceptance.test.selenium.wait.timeout.seconds", Integer.class, 10);
    }

    public String getBrowser() {
        return environment.getProperty("ui.acceptance.test.browser");
    }

    public String getHubUrl() {
        return environment.getProperty("ui.acceptance.test.selenium.hub.url");
    }
}
