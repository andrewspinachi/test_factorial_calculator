package com.hostelworld.ui.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.hostelworld.ui.context"
})
public class SpringConfig {
    @Bean
    public ScenarioContext scenarioContext() {
        return new ScenarioContext();
    }
}
