package com.odde.bbuddy.acceptancetest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/cucumber/resources/features", glue = {"com.odde.bbuddy.acceptancetest.steps"}, format = {"pretty"})
public class RunCukesTest {
}
