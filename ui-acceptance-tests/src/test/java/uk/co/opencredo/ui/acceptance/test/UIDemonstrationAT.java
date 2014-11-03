package uk.co.opencredo.ui.acceptance.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = { "@ui-demo", "~@ignore" },
        format = { "json:target/UIDemonstrationAT.json", "html:target/cucumber-report/UIDemonstrationAT" })
public class UIDemonstrationAT
{
}
