package com.hostelworld.ui;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

public class FirefoxDriverManager extends DriverManager {

    private GeckoDriverService ffService;

    @Override
    public void startService() {
        if (null == ffService) {
            try {
                System.setProperty("webdriver.gecko.driver", "src/WebDrivers/geckodriver.exe");
                ffService = GeckoDriverService.createDefaultService();
                ffService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void stopService() {
        if (null != ffService && ffService.isRunning())
            ffService.stop();
    }

    @Override
    protected void createDriver() {
        driver = new FirefoxDriver();
    }
}



