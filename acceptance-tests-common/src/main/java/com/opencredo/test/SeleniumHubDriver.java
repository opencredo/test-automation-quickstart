package com.opencredo.test;

import com.opencredo.test.utils.Nonchalantly;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class SeleniumHubDriver extends SharedDriver {


    public SeleniumHubDriver(String browser, String seleniumHubUrl) {
        super(getRemoteWebDriver(browser, seleniumHubUrl));
    }

    private static WebDriver getRemoteWebDriver(String browser, String hubUrl) {
        if (DRIVER != null) return DRIVER;
        Capabilities capability = getRemoteCapabilities(browser);
        URL remoteAddress = Nonchalantly.invoke(() -> new URL(hubUrl));
        return new RemoteWebDriver(remoteAddress, capability);
    }

    private static Capabilities getRemoteCapabilities(String browser) {
        switch (browser) {
            case BrowserType.FIREFOX:
                return new FirefoxOptions();
            case BrowserType.CHROME:
                return new ChromeOptions();
            default:
                throw new RuntimeException("Framework does not support browser \"" + browser + "\"");
        }
    }

}
