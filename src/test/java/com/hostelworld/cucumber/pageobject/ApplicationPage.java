package com.hostelworld.cucumber.pageobject;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Getter
public class ApplicationPage {

    protected final WebDriver driver;

    public ApplicationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
