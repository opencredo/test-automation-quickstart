package com.opencredo.api.acceptance.test.step.definitions;


import com.opencredo.api.acceptance.test.config.spring.TestConfig;
import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import com.opencredo.api.acceptance.test.interaction.objects.GithubStatusApi;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes= TestConfig.class)
public class ServiceStatusStepDefs {
    @Autowired
    GithubStatusApi githubStatus;

    @Given("^that Github is up and running with a \"([^\"]*)\" status$")
    public void that_Github_is_up_and_running_with_a_status(String expectedStatus) throws Throwable {
        assertEquals(expectedStatus, githubStatus.getServiceStatus().status);
    }
}
