package com.opencredo.ui.acceptance.test.step.definitions;

import com.opencredo.ui.acceptance.test.config.spring.TestConfig;
import com.opencredo.ui.acceptance.test.interaction.objects.GoogleSearchPage;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import com.opencredo.ui.acceptance.test.common.World;
import com.opencredo.ui.acceptance.test.interaction.objects.GoogleSearchPage;
import com.opencredo.ui.acceptance.test.config.spring.TestConfig;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes= TestConfig.class)
public class DemonstrationStepDefs extends AbstractStepDefinition {
    @Autowired
    private GoogleSearchPage googleSearchPage;

    /**
     * pass the current cucumber scenario to abstract class to
     * support writing to cucumber test report due to cucumber
     * limitations
     */
    @Before
    public void before(Scenario scenario) {
        super.before(scenario);
    }

    @Given("^I am on the Google search page")
    public void I_am_on_the_google_search_page() throws Throwable {
        //demonstrates of writing to the cucumber test report
        embedTextInReport("Navigating to page " + googleSearchPage.getPath());

        //navigate to the page and wait for load to complete
        googleSearchPage.goToAndWait();
    }

    @When("^I search for \"(.+)\"$")
    public void I_search_for(String searchText) throws Throwable {
        //utilise a method on the page object to perform an interaction
        googleSearchPage.search(searchText);
    }

    @Then("^the site \"(.+)\" should be present in the results$")
    public void the_result_should_contain_url(String resultUrl) throws Throwable {
        //utilise a method on the page object to perform an assertion
        assertTrue(googleSearchPage.isSearchResultPresent(resultUrl));
    }
}
