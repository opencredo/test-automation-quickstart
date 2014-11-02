package uk.co.opencredo.ui.acceptance.test.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import uk.co.opencredo.ui.acceptance.test.spring.config.TestConfig;

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
