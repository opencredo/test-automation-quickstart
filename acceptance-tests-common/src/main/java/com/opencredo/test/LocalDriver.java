package com.opencredo.test;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Creates a browser session which can be used to interact with a web UI.
 * This class allows for a single session to be re-used across multiple
 * test cases for increased speed.
 */

public class LocalDriver extends SharedDriver {
    private static final Thread CLOSE_THREAD = new Thread(LocalDriver::quitGlobalInstance);

    public LocalDriver(String browser, boolean isProxyEnabled, Proxy proxy) {
        super(getDriver(browser, isProxyEnabled, proxy));
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    private static void quitGlobalInstance() {
        WebDriver driver = DRIVER;
        DRIVER = null;
        if (driver != null) {
            driver.quit();
        }
    }

    private static WebDriver getDriver(String browser, boolean isProxyEnabled, Proxy proxy) {
        if (DRIVER != null) return DRIVER;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (isProxyEnabled && proxy != null)
            capabilities.setCapability(CapabilityType.PROXY, proxy);

        DRIVER = getBrowserDriver(browser, capabilities);

        return DRIVER;
}


    private static WebDriver getBrowserDriver(String browser, DesiredCapabilities capabilities) {
        switch (browser) {
            case BrowserType.FIREFOX:
                return getFirefoxDriver(capabilities);
            case BrowserType.GOOGLECHROME:
                return getChromeDriver(capabilities);
            default:
                throw new RuntimeException("Framework does not support browser \"" + browser + "\"");
        }
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
