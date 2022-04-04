package com.hostelworld.cucumber.actions;

import com.hostelworld.cucumber.context.ScenarioContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class BrowserActions {

    private static Logger logger = LoggerFactory.getLogger(BrowserActions.class);


    @Autowired
    ScenarioContext scenarioContext;

    private WebDriver webDriver;


    private final int WAIT_SECONDS = 30;


    private WebDriver getWebDriver() {
        if (webDriver != null) {
            return webDriver;
        }
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();

        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().timeouts().implicitlyWait(WAIT_SECONDS, TimeUnit.SECONDS);
        return webDriver;
    }
}
