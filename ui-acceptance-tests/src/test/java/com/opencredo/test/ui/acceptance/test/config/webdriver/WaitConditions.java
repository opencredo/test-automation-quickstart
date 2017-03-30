package com.opencredo.test.ui.acceptance.test.config.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitConditions {
    public static ExpectedCondition<Boolean> urlContains(final String text) {
        return new ExpectedCondition<Boolean>() {
            private String currentUrl = "";

            @Override
            public Boolean apply(WebDriver driver) {
                currentUrl = driver.getCurrentUrl();
                return currentUrl.contains(text);
            }

            @Override
            public String toString() {
                return String.format("URL to contain \"%s\". Current URL: \"%s\"", text, currentUrl);
            }
        };
    }

    public static ExpectedCondition<Boolean> pageContainsText(final String text) {
        return new ExpectedCondition<Boolean>() {
            private String currentPage = "";

            @Override
            public Boolean apply(WebDriver driver) {
                currentPage = driver.getPageSource();
                return currentPage.contains(text);
            }

            @Override
            public String toString() {
                return String.format("Page to contain \"%s\"", text);
            }
        };
    }
}
