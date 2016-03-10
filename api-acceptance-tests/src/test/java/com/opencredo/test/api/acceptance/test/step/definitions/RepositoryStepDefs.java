package com.opencredo.test.api.acceptance.test.step.definitions;


import com.opencredo.test.api.acceptance.test.interaction.api.objects.GithubApi;
import com.opencredo.test.api.acceptance.test.interaction.dto.Repository;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositoryStepDefs extends AbstractStepDefinition {
    @Autowired
    private GithubApi githubApi;

    private List<Repository> repositoryList = new ArrayList<>();

    @Before
    public void before(Scenario scenario) {
        super.before(scenario);
    }

    @When("^I retrieve a list of repositories for organisation \"([^\"]*)\"$")
    public void I_retrieve_a_list_of_repositories_for_organisation(String organisation) throws Throwable {
        repositoryList = githubApi.getRepositoryListForOrganisation(organisation);

        embedTextInReport("Received list containing '" + repositoryList.size() + "' repositories");
    }

    @Then("^the following repositories should be present in the repository list$")
    public void theFollowingRepositoriesShouldBePresentInTheRepositoryList(final List<String> expectedRepositories) throws Throwable {

        expectedRepositories.forEach(expectedRepository -> {
            assertThat(repositoryList).extracting("name").contains(expectedRepository);
        });
    }
}
