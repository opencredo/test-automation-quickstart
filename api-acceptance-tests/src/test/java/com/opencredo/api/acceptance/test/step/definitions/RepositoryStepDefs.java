package com.opencredo.api.acceptance.test.step.definitions;

import com.opencredo.api.acceptance.test.common.RepositoryResponse;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import com.opencredo.api.acceptance.test.config.spring.TestConfig;
import com.opencredo.api.acceptance.test.interaction.objects.GithubApi;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes= TestConfig.class)
public class RepositoryStepDefs {
    @Autowired
    private GithubApi githubApi;

    private List<RepositoryResponse> repositoryList = new ArrayList<>();

    @When("^I retrieve a list of repositories for user \"([^\"]*)\"$")
    public void I_retrieve_a_list_of_repositories_for_user(String user) throws Throwable {
        repositoryList = githubApi.getRepositoryListForUser(user);
    }

    @Then("^there the repository \"([^\"]*)\" should be present in the repository list$")
    public void there_the_repository_should_be_present_in_the_repository_list(String expectedRepository) throws Throwable {
        List<String> repositoryNames = new ArrayList<>();
        for (int i =0; i<repositoryList.size(); i++) {
            repositoryNames.add(repositoryList.get(i).name);
        }

        assertTrue("Repository with name '" + expectedRepository + "' not found in list",
                repositoryNames.contains(expectedRepository));
    }
}
