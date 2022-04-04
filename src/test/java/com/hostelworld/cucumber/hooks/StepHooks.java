package com.hostelworld.cucumber.hooks;

import com.hostelworld.cucumber.config.CucumberConfiguration;
import com.hostelworld.ui.context.SpringConfig;
import io.cucumber.java.After;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;
import org.springframework.test.context.ContextConfiguration;

@Slf4j

@ContextConfiguration(classes = {
        SpringConfig.class,
        CucumberConfiguration.class,
})
@AllArgsConstructor
public class StepHooks {

    private final WebDriver driver;

    @After(order = 0)
    public void clearDriver() {
        driver.switchTo().defaultContent();

        WebStorage webStorage = (WebStorage) driver;
        webStorage.getSessionStorage().clear();
        webStorage.getLocalStorage().clear();

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
