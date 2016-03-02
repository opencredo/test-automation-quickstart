package com.opencredo.test.api.acceptance.test.step.definitions;


import com.opencredo.test.config.spring.ApiTestConfig;
import com.opencredo.test.interaction.api.objects.GithubStatusApi;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ContextConfiguration(classes= ApiTestConfig.class)
public class ServiceStatusStepDefs extends AbstractStepDefinition{
    @Autowired
    GithubStatusApi githubStatus;

    @Before
    public void before(Scenario scenario) {
        super.before(scenario);
    }

    @Given("^that Github is up and running with a \"([^\"]*)\" status$")
    public void that_Github_is_up_and_running_with_a_status(String expectedStatus) throws Throwable {
        assertThat(expectedStatus).isEqualToIgnoringCase(githubStatus.getServiceStatus().status);
    }
}
