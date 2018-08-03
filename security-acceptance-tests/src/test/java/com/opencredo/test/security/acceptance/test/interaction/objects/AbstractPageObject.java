package com.opencredo.test.security.acceptance.test.interaction.objects;

import com.opencredo.test.security.acceptance.test.config.webdriver.WaitConditions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

/**
 * Reusable methods for all page objects
 */
public abstract class AbstractPageObject {
    private final WebDriver driver;
    private final int waitTimeOutSeconds;
    private String path;

    public AbstractPageObject(String path, WebDriver driver, int waitTimeOutSeconds) {
        this.path = path;
        this.driver = driver;
        this.waitTimeOutSeconds = waitTimeOutSeconds;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getPath() {
        return path;
    }

    public void goToAndWait() {
        getDriver().navigate().to(path);
        ensure_is_current();
    }

    public void ensure_is_current() {
        waitUntilTrueOrTimeout(WaitConditions.urlContains(path));
    }

    public boolean is_text_present(String text) {
        waitUntilTrueOrTimeout(WaitConditions.pageContainsText(text));
        return true;
    }

    /**
     * wait until condition is true or timeout is reached
     */
    protected <V> V waitUntilTrueOrTimeout(ExpectedCondition<V> isTrue) {
        Wait<WebDriver> wait = new WebDriverWait(this.driver, waitTimeOutSeconds)
                .ignoring(StaleElementReferenceException.class);
        try {
            return wait.until(isTrue);
        } catch (TimeoutException rte) {
            throw new TimeoutException(rte.getMessage());
        }
    }

    public void setText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void submit(WebElement element) {
        element.submit();
    }

    public void selectDropdownByText(WebElement element, String visibleText) {
        Select filterSelect = new Select(element);
        waitForDropdownItems(element);
        filterSelect.selectByVisibleText(visibleText);
    }

    private void waitForDropdownItems(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), waitTimeOutSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement find(By locator) {
        try {
            return getDriver().findElement(locator);
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException(ex.getMessage());
        }
    }
}
