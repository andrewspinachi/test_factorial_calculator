package com.hostelworld.cucumber.hooks;

import com.hostelworld.cucumber.actions.TakeScreenshot;
import com.hostelworld.cucumber.context.Context;
import com.hostelworld.cucumber.context.ScenarioContext;
import com.hostelworld.ui.DriverManager;
import com.hostelworld.ui.DriverMangerFactory;
import com.hostelworld.ui.DriverTypes;
import io.cucumber.messages.Messages;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class StepHooks {

    private static Logger logger = LoggerFactory.getLogger(StepHooks.class);

    @Autowired
    private ScenarioContext scenarioContext;

    private WebDriver webDriver;

    private DriverManager driverManager;


    @Before()
    public void before() {
        scenarioContext.resetContext();
        scenarioContext.saveData(Context.TIMESTAMP, LocalDateTime.now(Clock.systemUTC()));

        driverManager = DriverMangerFactory.getManager(DriverTypes.CHROME);
        webDriver = driverManager.getDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("http://qainterview.pythonanywhere.com/factorial");
    }

    @After
    public void logScreenShotEvidence(Messages.GherkinDocument.Feature.Scenario sc) throws IOException {

        Path location = Paths.get("target/screenshots/" + File.separator + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + File.separator + scenarioContext.getScenarioName() + "/");
        if (!Files.exists(location)) {
            Files.createDirectories(location);
        }
        TakeScreenshot.takeScreen(webDriver, scenarioContext.getScenarioName());
        driverManager.quitDriver();
    }

}
