package com.opencredo.test.ui.acceptance.test.step.definitions;

import com.opencredo.test.ui.acceptance.test.config.TestWorld;
import com.opencredo.test.ui.acceptance.test.config.spring.UiTestConfig;
import cucumber.api.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = UiTestConfig.class)
public abstract class AbstractStepDefinition {

    @Autowired
    protected TestWorld testWorld;
    private Scenario scenario;

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
