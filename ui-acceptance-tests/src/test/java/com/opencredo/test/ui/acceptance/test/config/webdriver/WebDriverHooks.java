package com.opencredo.test.ui.acceptance.test.config.webdriver;

import com.opencredo.test.LocalDriver;
import com.opencredo.test.ui.acceptance.test.config.spring.UiTestConfig;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = UiTestConfig.class)
public class WebDriverHooks {

    @Autowired
    private LocalDriver localDriver;

    /**
     * Delete all cookies at the start of each ui scenario to avoid
     * shared state between tests
     */
    @Before
    public void deleteAllCookies() {
        this.localDriver.manage().deleteAllCookies();
    }

    /**
     * Embed a screenshot in test report if a ui scenario is marked as failed
     */
    @After()
    public void embedScreenshotIfFailed(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + localDriver.getCurrentUrl());
                if (localDriver.getWrappedDriver() instanceof TakesScreenshot) {
                    byte[] screenshot = localDriver.getScreenshotAs(OutputType.BYTES);
                    scenario.embed(screenshot, "image/png");
                }
            } catch (Throwable somePlatformsDontSupportScreenshotsOrBrowserHasDied) {
                somePlatformsDontSupportScreenshotsOrBrowserHasDied.printStackTrace(System.err);
            }
        }
    }
}
