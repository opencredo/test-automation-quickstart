package com.opencredo.test.ui.acceptance.test.interaction.objects;

import com.opencredo.test.LocalDriver;
import org.openqa.selenium.By;

public class GoogleSearchPageObject extends AbstractPageObject {
    public static final String PATH = "/";

    private final By searchTextbox = By.name("q");

    public GoogleSearchPageObject(String baseUrl, LocalDriver driver, int waitTimeOutSeconds) {
        super(baseUrl + PATH, driver, waitTimeOutSeconds);
    }

    public void search(String searchText) {
        setText(find(searchTextbox), searchText);
        submit(find(searchTextbox));
    }

    public boolean isSearchResultPresent(String searchResultUrl) {
        return isTextPresent(searchResultUrl);
    }
}
