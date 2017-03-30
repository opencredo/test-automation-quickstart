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

    public boolean isGridEnabled(){
        return Boolean.valueOf(environment.getProperty("ui.acceptance.test.selenium.grid.enabled"));
    }

    public String getDefaultBrowser(){
        return environment.getProperty("ui.acceptance.test.selenium.default.browser");
    }

    public String getDefaultHubUrl(){
        return environment.getProperty("ui.acceptance.test.selenium.default.hub.url");
    }
}
