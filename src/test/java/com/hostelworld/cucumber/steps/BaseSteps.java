package com.hostelworld.cucumber.steps;

import com.hostelworld.cucumber.config.CucumberConfiguration;
import com.hostelworld.ui.context.ScenarioContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;

@NoArgsConstructor
@AllArgsConstructor
public class BaseSteps {
    protected WebDriver driver;

    protected ScenarioContext scenarioContext;

    protected CucumberConfiguration cucumberConfiguration;
}
