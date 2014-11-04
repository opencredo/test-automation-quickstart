package com.opencredo.ui.acceptance.test.config.webdriver;

import com.opencredo.ui.acceptance.test.common.World;
import com.opencredo.ui.acceptance.test.config.spring.TestConfig;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Perform actions before and after execution of each test with cucumber hooks
 */
@ContextConfiguration(classes = TestConfig.class)
public class WebDriverHooks {

    @Autowired
    private SharedDriver sharedDriver;

    @Autowired
    private World world;

    /**
     * Delete all cookies at the start of each ui scenario to avoid
     * shared state between tests
     */
    @Before
    public void deleteAllCookies() {
        this.sharedDriver.manage().deleteAllCookies();
    }

    /**
     * Embed a screenshot in test report if a ui scenario is marked as failed
     */
    @After()
    public void embedScreenshotIfFailed(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + sharedDriver.getCurrentUrl());
                if (sharedDriver.getWrappedDriver() instanceof TakesScreenshot) {
                    byte[] screenshot = sharedDriver.getScreenshotAs(OutputType.BYTES);
                    scenario.embed(screenshot, "image/png");
                }
            } catch (Throwable somePlatformsDontSupportScreenshotsOrBrowserHasDied) {
                somePlatformsDontSupportScreenshotsOrBrowserHasDied.printStackTrace(System.err);
            }
        }
    }
}
