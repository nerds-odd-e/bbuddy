package com.odde.bbuddy.acceptancetest;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = {"com.odde.bbuddy.acceptancetest.steps"}, format = {
        "pretty", "html:target/cucumber", "json:target/cucumber-report.json"})
public class RunCukesTest {
}
