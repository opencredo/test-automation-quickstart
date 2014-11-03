package uk.co.opencredo.api.acceptance.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = { "@api-demo", "~@ignore" },
        format = { "json:target/APIDemonstrationAT.json", "html:target/cucumber-report/APIDemonstrationAT" })
public class APIDemonstrationAT
{
}
