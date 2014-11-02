package uk.co.opencredo.ui.acceptance.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "DemonstrateUiTestFramework.feature",
        tags = { "@demo", "~@ignore" },
        format = { "json:target/DemonstrationAT.json", "html:target/cucumber-report/DemonstrationAT" })
public class DemonstrationAT
{
}
