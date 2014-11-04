package com.opencredo.ui.acceptance.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = { "@ui-demo", "~@ignore" },
        format = { "json:target/ParallelUIDemonstrationAT.json", "html:target/cucumber-report/ParallelUIDemonstrationAT" })
public class ParallelUIDemonstrationAT
{
}
