package com.opencredo.test.email.acceptance.test.step.definitions;

import com.opencredo.test.email.acceptance.test.config.spring.EmailTestConfig;
import cucumber.api.Scenario;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = EmailTestConfig.class)
public abstract class AbstractStepDefinition {

    protected Scenario scenario;

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
