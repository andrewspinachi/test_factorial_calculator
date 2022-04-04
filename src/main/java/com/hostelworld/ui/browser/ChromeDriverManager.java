package com.hostelworld.ui.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.springframework.stereotype.Service;

import java.util.logging.Level;

@Service
public class ChromeDriverManager extends AbstractDriverManager {

    @Override
    protected void startService() {
        WebDriverManager.chromedriver().setup();
    }

    @Override
    protected void createDriver() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
        driver = new ChromeDriver(options);
    }
}
