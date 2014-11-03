package uk.co.opencredo.ui.acceptance.test.step.definitions;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
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
public class DemonstrationStepDefs extends AbstractStepDefinition {
    @Autowired
    private World world;

    @Autowired
    private GoogleSearchPage googleSearchPage;

    @Before
    public void before(Scenario scenario) {
        super.before(scenario);
    }

    @Given("^I am on the Google search page")
    public void I_am_on_the_google_search_page() throws Throwable {
        embedTextInReport("Navigating to page " + googleSearchPage.getPath());
        googleSearchPage.goToAndWait();
    }

    @When("^I search for \"(.+)\"$")
    public void I_search_for(String searchText) throws Throwable {
        googleSearchPage.search(searchText);
    }

    @Then("^there the site \"(.+)\" should be present in the results$")
    public void the_result_should_contain_url(String resultUrl) throws Throwable {
        assertTrue(googleSearchPage.isSearchResultPresent(resultUrl));
    }
}
