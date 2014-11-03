package uk.co.opencredo.ui.acceptance.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = { "@demo", "~@ignore" },
        format = { "json:target/ParallelDemonstrationAT.json", "html:target/cucumber-report/ParallelDemonstrationAT" })
public class ParallelDemonstrationAT
{
}
