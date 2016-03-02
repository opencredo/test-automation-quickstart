package com.opencredo.api.acceptance.test;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:cucumber/",
        tags = {"@api-demo", "~@ignore"},
        format = {"html:target/cucumber-report/APIDemonstrationAT"})
public class ApiDemonstrationAT {
}
