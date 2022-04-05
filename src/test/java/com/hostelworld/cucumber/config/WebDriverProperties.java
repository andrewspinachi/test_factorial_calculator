package com.hostelworld.cucumber.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "driver")
@PropertySource(value = "classpath:properties/webdriver.properties")
public class WebDriverProperties {

    private int pollingInterval;

    private int displayTimeout;

    private int popupDisplayTimeout;

    private int clickTimeout;

}
