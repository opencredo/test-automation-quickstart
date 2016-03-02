package com.opencredo.ui.acceptance.test.step.definitions;

import com.opencredo.ui.acceptance.test.config.spring.TestConfig;
import com.opencredo.ui.acceptance.test.interaction.objects.GoogleSearchPageObject;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ContextConfiguration(classes= TestConfig.class)
public class GoogleSearchStepDefs extends AbstractStepDefinition {
    @Autowired
    private GoogleSearchPageObject googleSearchPageObject;

    @Before
    public void before(Scenario scenario) {
        super.before(scenario);
    }

    @Given("^I am on the Google search page")
    public void I_am_on_the_google_search_page() throws Throwable {
        googleSearchPageObject.goToAndWait();
    }

    @When("^I search for \"(.+)\"$")
    public void I_search_for(String searchText) throws Throwable {
        googleSearchPageObject.search(searchText);
    }

    @Then("^the site \"(.+)\" should be present in the results$")
    public void the_result_should_contain_url(String resultUrl) throws Throwable {
        assertThat(googleSearchPageObject.isSearchResultPresent(resultUrl)).isTrue();
    }
}
