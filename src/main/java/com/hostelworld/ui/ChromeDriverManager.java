package com.hostelworld.ui;

import com.hostelworld.junit.exceptions.CustomTestException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class ChromeDriverManager extends DriverManager {

    private ChromeDriverService chService;

    @Override
    protected void startService() {
        if (null == chService) {
            try {
                chService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("chromedriver.exe"))
                        .usingAnyFreePort()
                        .build();
                chService.start();
            } catch (Exception e) {
                throw new CustomTestException("Service failed to start: {}", e.getMessage());
            }
        }
    }

    @Override
    protected void stopService() {
        if (null != chService && chService.isRunning())
            chService.stop();
    }

    @Override
    protected void createDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("tets-type");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(chService, capabilities);
    }
}
