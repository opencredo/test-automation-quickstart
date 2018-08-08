package com.opencredo.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Creates a browser session which can be used to interact with a web UI.
 * This class allows for a single session to be re-used across multiple
 * test cases for increased speed.
 */

public class SharedDriver extends EventFiringWebDriver {
    protected static WebDriver DRIVER;


    private static final Thread CLOSE_THREAD = new Thread(SharedDriver::quitGlobalInstance);

    public SharedDriver(WebDriver driver) {
        super(driver);
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    private static void quitGlobalInstance() {
        WebDriver driver = DRIVER;
        DRIVER = null;
        if (driver != null) {
            driver.quit();
        }
    }

    protected static WebDriver getFirefoxDriver(DesiredCapabilities capabilities) {
        FirefoxOptions firefoxOptions = new FirefoxOptions().merge(capabilities);
        return new FirefoxDriver(firefoxOptions);
    }

    protected static WebDriver getChromeDriver(DesiredCapabilities capabilities) {
        ChromeOptions chromeOptions = new ChromeOptions().merge(capabilities);
        return new ChromeDriver(chromeOptions);
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
}
