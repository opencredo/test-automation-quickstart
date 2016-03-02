package com.opencredo.test.ui.acceptance.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Run cucumber scenarios tagged as "@ui-demo"
 * Write HTML report out to UIDemonstrationAT folder
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:cucumber/",
        tags = { "@ui-demo", "~@ignore" },
        format = { "html:target/cucumber-report/UIDemonstrationAT" })
public class UiDemonstrationAT
{
}
