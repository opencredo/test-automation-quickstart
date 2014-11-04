package com.opencredo.api.acceptance.test.step.definitions;


import com.opencredo.api.acceptance.test.config.spring.TestConfig;
import com.opencredo.api.acceptance.test.interaction.objects.GithubStatusApi;
import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;

/**
 * Step definitions relating to the GitHub Service Status API
 */
@ContextConfiguration(classes= TestConfig.class)
public class ServiceStatusStepDefs extends AbstractStepDefinition{
    @Autowired
    GithubStatusApi githubStatus;

    @Given("^that Github is up and running with a \"([^\"]*)\" status$")
    public void that_Github_is_up_and_running_with_a_status(String expectedStatus) throws Throwable {
        //utilise the api object to perform a request
        String currentServiceStatus = githubStatus.getServiceStatus().status;
        assertEquals(expectedStatus, currentServiceStatus);

        //demonstrates saving information to world object for sharing across step def classes
        world.sharedState.put("serviceStatus", currentServiceStatus);
    }
}
