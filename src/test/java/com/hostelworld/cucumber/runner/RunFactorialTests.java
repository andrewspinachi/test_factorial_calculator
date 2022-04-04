package com.hostelworld.cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@run",
        features = {"src/test/resources/features"},
        glue = {"com.hostelworld.cucumber.steps", "com.hostelworld.cucumber.config"},
        stepNotifications = true,
        plugin = {"pretty"}
)
public class RunFactorialTests {
}
