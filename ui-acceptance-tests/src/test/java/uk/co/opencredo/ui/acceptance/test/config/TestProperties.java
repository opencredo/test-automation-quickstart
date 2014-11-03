package uk.co.opencredo.ui.acceptance.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Used to retrieve properties and make them available to tests
 */
public class TestProperties {
    @Autowired
    private Environment environment;

    public String getApplicationBaseUri() {
        return environment.getProperty("ui.acceptance.test.application.base.url");
    }

    public int getSeleniumWaitTimeOutSeconds() {
        return environment.getProperty("ui.acceptance.test.selenium.wait.timeout.seconds", Integer.class, 10);
    }
}
