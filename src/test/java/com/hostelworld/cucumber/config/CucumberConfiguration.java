package com.hostelworld.cucumber.config;

import com.hostelworld.ui.browser.DriverManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Getter
@AllArgsConstructor
@Configuration
@ComponentScan(basePackages = {
        "com.hostelworld.ui.browser",
        "com.hostelworld.cucumber.config",
        "com.hostelworld.cucumber.pageobject",
        "com.hostelworld.cucumber.actions"
})
@EnableConfigurationProperties
public class CucumberConfiguration {

    private final DriverManager driverManager;

    @Bean(destroyMethod = "quit")
    public WebDriver driver() {
        return driverManager.getDriver();
    }
}
