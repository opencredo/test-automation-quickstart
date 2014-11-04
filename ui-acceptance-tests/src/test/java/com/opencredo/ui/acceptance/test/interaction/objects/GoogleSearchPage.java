package com.opencredo.ui.acceptance.test.interaction.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Provides methods for interacting with the Google search page
 */
public class GoogleSearchPage extends AbstractPageObject {
    public static final String PATH = "/";

    //page elements
    private final By searchTextbox = By.name("q");

    public GoogleSearchPage(String baseUrl, WebDriver driver, int waitTimeOutSeconds) {
        super(baseUrl + PATH, driver, waitTimeOutSeconds);
    }

    public void search(String searchText) {
        setText(find(searchTextbox), searchText);
        submit(find(searchTextbox));
    }

    public boolean isSearchResultPresent(String searchResultUrl) {
        return is_text_present(searchResultUrl);
    }
}
