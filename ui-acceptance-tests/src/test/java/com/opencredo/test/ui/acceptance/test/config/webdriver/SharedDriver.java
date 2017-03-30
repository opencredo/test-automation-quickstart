package com.opencredo.test.ui.acceptance.test.config.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

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

    private static WebDriver getRealDriver(String defaultBrowser,String defaultHubUrl) {
        if (REAL_DRIVER == null) {
            switch (defaultBrowser){
                case "firefox":
                    try {
                        DesiredCapabilities capability = DesiredCapabilities.firefox();
                        REAL_DRIVER = new RemoteWebDriver(new URL(defaultHubUrl), capability);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "chrome":
                    try {
                        DesiredCapabilities capability = DesiredCapabilities.chrome();
                        REAL_DRIVER = new RemoteWebDriver(new URL(defaultHubUrl), capability);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                default:REAL_DRIVER = new FirefoxDriver();
                    break;
            }
        }
        return REAL_DRIVER;
    }

    private static WebDriver getRealDriver() {
        if (REAL_DRIVER == null) {
            REAL_DRIVER = new FirefoxDriver();
        }
        return REAL_DRIVER;
    }

    public SharedDriver(String defaultBrowser,String defaultHubUrl) {
        super(getRealDriver(defaultBrowser,defaultHubUrl));
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }


    public SharedDriver() {
        super(getRealDriver());
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
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


