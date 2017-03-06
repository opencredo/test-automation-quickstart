package com.opencredo.test.ui.acceptance.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Used to retrieve properties and make them available to tests
 */
public class SecurityTestProperties {

    @Autowired
    private Environment environment;

    public String getApplicationBaseUrl() {
        return environment.getProperty("security.acceptance.test.application.base.url");
    }

    public int getSeleniumWaitTimeOutSeconds() {
        return environment.getProperty("security.acceptance.test.selenium.wait.timeout.seconds", Integer.class, 10);
    }

    public Boolean isProxyEnabled() {
        return environment.getProperty("security.acceptance.test.proxy.enabled",Boolean.class,false);
    }
    public String getProxyUrl() {
        return environment.getProperty("security.acceptance.test.proxy.url");
    }
    public String getProxyPort() {
        return environment.getProperty("security.acceptance.test.proxy.port");
    }
}
