package com.opencredo.ui.acceptance.test.config.webdriver;

import cucumber.api.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.internal.HttpClientFactory;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.lang.reflect.Field;

/**
 * Creates a browser session which can be used to interact with a web UI.
 * This class allows for a single session to be re-used across multiple
 * test cases for increased speed.
 */
public class SharedDriver extends EventFiringWebDriver {
    private static WebDriver REAL_DRIVER;
    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            quitGlobalInstance();
        }
    };

    private static void quitGlobalInstance() {
        WebDriver driver = REAL_DRIVER;
        REAL_DRIVER = null;
        if (driver != null) {
            driver.quit();
        }
    }

    static {
        try {
            Field field = HttpCommandExecutor.class.getDeclaredField("httpClientFactory");

            HttpClientFactory factory = new HttpClientFactory() {

            };
            field.setAccessible(true);
            field.set(HttpCommandExecutor.class, factory);

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    private static WebDriver getRealDriver() {
        if (REAL_DRIVER == null) {
            REAL_DRIVER = new FirefoxDriver();
        }
        return REAL_DRIVER;
    }

    public SharedDriver() {
        super(getRealDriver());
        getWrappedDriver().manage().window().setSize(new Dimension(1024, 1280));
        register(new AbstractWebDriverEventListener() {
            @Override
            public void afterNavigateTo(String url, WebDriver driver) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        try {
            super.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshotIfFailed(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + getCurrentUrl());
                if (getWrappedDriver() instanceof TakesScreenshot) {
                    byte[] screenshot = getScreenshotAs(OutputType.BYTES);
                    scenario.embed(screenshot, "image/png");
                }
            } catch (Throwable somePlatformsDontSupportScreenshotsOrBrowserHasDied) {
                somePlatformsDontSupportScreenshotsOrBrowserHasDied.printStackTrace(System.err);
            }
        }
    }
}
