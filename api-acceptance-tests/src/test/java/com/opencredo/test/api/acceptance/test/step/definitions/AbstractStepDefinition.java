package com.opencredo.test.api.acceptance.test.step.definitions;

import com.opencredo.test.api.acceptance.test.config.TestWorld;
import com.opencredo.test.api.acceptance.test.config.spring.ApiTestConfig;
import cucumber.api.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = ApiTestConfig.class)
public abstract class AbstractStepDefinition {

    protected Scenario scenario;

    @Autowired
    protected TestWorld testWorld;

    /**
     * Get a reference to the current cucumber scenario
     * Supports writing text and xml to report within test steps
     *
     * @param scenario
     */
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    protected void embedTextInReport(String text) {
        scenario.write(text);
    }

    protected void embedXmlInReport(String xml) {
        xml = "<textarea readonly>" + xml + "</textarea>";
        scenario.write(xml);
    }
}
