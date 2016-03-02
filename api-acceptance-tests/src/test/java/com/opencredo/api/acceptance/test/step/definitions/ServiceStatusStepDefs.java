package com.opencredo.api.acceptance.test.step.definitions;


import com.opencredo.api.acceptance.test.config.spring.TestConfig;
import com.opencredo.api.acceptance.test.interaction.objects.GithubStatusApi;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes= TestConfig.class)
public class ServiceStatusStepDefs extends AbstractStepDefinition{
    @Autowired
    GithubStatusApi githubStatus;

    @Before
    public void before(Scenario scenario) {
        super.before(scenario);
    }

    @Given("^that Github is up and running with a \"([^\"]*)\" status$")
    public void that_Github_is_up_and_running_with_a_status(String expectedStatus) throws Throwable {
        assertEquals(expectedStatus, githubStatus.getServiceStatus().status);
    }
}
