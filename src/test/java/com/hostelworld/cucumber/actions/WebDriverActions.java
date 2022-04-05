package com.hostelworld.cucumber.actions;

import com.hostelworld.cucumber.config.WebDriverProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Component
public class WebDriverActions {

    private static WebDriverProperties webDriverProperties;
    private static WebDriver driver;

    @Autowired
    public WebDriverActions(WebDriver driver, WebDriverProperties webDriverProperties) {
        WebDriverActions.driver = driver;
        WebDriverActions.webDriverProperties = webDriverProperties;
    }

    public static void waitForElementDisplayed(WebElement element) {
        waitForElementDisplayed(element, webDriverProperties.getDisplayTimeout());
    }

    public static void waitForElementDisplayed(WebElement element, int timeoutMillis) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(webDriverProperties.getDisplayTimeout()))
                .pollingEvery(Duration.ofMillis(webDriverProperties.getPollingInterval()))
                .ignoring(NoSuchElementException.class)
                // IE throws org.openqa.selenium.WebDriverException: Returned value cannot be converted to WebElement:
                // {message=An element could not be located on the page using the given search parameters., error=no such element}
                .ignoring(WebDriverException.class)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static Boolean isDisplayed(WebElement element) {
        return isDisplayed(element, webDriverProperties.getDisplayTimeout());
    }

    public static Boolean isDisplayed(WebElement element, int timeoutMillis) {
        log.info("Checking for element presence: [{}]", element);

        try {
            waitForElementDisplayed(element, timeoutMillis);
        } catch (TimeoutException e) {
            log.info("Element is NOT present: [{}]", element);
            return false;
        }

        return true;
    }

    public static Boolean isDisplayedPopup(WebElement element) {
        return isDisplayed(element, webDriverProperties.getPopupDisplayTimeout());
    }

    public static Boolean isClickable(WebElement element) {
        log.info("Checking for element to be clickable: [{}]", element);

        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofMillis(webDriverProperties.getClickTimeout()))
                    .pollingEvery(Duration.ofMillis(webDriverProperties.getPollingInterval()))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            log.info("Element is NOT clickable: [{}]", element);
            return false;
        }

        return true;
    }

    public static void click(WebElement element) {
        waitForElementDisplayed(element);

        Wait<WebDriver> clickWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(webDriverProperties.getClickTimeout()))
                .pollingEvery(Duration.ofMillis(webDriverProperties.getPollingInterval()))
                .ignoring(NoSuchElementException.class)
                // IE throws org.openqa.selenium.WebDriverException: Returned value cannot be converted to WebElement:
                // {message=An element could not be located on the page using the given search parameters., error=no such element}
                .ignoring(WebDriverException.class)
                .ignoring(StaleElementReferenceException.class);
        clickWait.until(ExpectedConditions.elementToBeClickable(element));

        String elementText = getText(element);
        log.info("Clicked [{}]", StringUtils.isEmpty(elementText) ? element : elementText);
    }

    public static String getText(WebElement element) {
        waitForElementDisplayed(element);

        log.info("Getting text for [{}]", element);
        return element.getText();
    }

    public static void populate(WebElement element, CharSequence value) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(webDriverProperties.getClickTimeout()))
                .pollingEvery(Duration.ofMillis(webDriverProperties.getPollingInterval()))
                .ignoring(NoSuchElementException.class)
                // IE throws org.openqa.selenium.WebDriverException: Returned value cannot be converted to WebElement:
                // {message=An element could not be located on the page using the given search parameters., error=no such element}
                .ignoring(WebDriverException.class)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(element));

        log.info("Populating [{}] with [{}]", element, value);
        element.clear();
        element.sendKeys(value);
    }

}
