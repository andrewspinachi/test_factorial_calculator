package com.hostelworld.cucumber.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObjectFactory {

    WebDriver driver;

    public PageObjectFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
