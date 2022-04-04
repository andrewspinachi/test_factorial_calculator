package com.hostelworld.ui.browser;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class AbstractDriverManager implements DriverManager {
    protected WebDriver driver;

    protected abstract void startService();

    protected abstract void createDriver();

    public WebDriver getDriver() {
        if (null == driver) {
            startService();
            createDriver();

        }

        return driver;
    }

    protected void setCapabilityIfPresent(DesiredCapabilities caps, String capabilityName, String value) {
        if (!StringUtils.isEmpty(value)) {
            caps.setCapability(capabilityName, value);
        }
    }
}
