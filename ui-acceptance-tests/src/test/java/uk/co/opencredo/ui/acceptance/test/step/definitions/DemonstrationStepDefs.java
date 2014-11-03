package uk.co.opencredo.ui.acceptance.test.step.definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import uk.co.opencredo.ui.acceptance.test.common.World;
import uk.co.opencredo.ui.acceptance.test.interaction.objects.GoogleSearchPage;
import uk.co.opencredo.ui.acceptance.test.config.spring.TestConfig;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes= TestConfig.class)
public class DemonstrationStepDefs {
    @Autowired
    private World world;

    @Autowired
    private GoogleSearchPage googleSearchPage;

    @Given("^I am on the Google search page")
    public void I_am_on_the_google_search_page() throws Throwable {
        googleSearchPage.goToAndWait();
    }

    @When("^I search for \"(.+)\"$")
    public void I_search_for(String searchText) throws Throwable {
        googleSearchPage.search(searchText);
    }

    @Then("^there the site \"(.+)\" should be present in the results$")
    public void the_result_should_contail_url(String resultUrl) throws Throwable {
        assertTrue(googleSearchPage.isSearchResultPresent(resultUrl));
    }
}
