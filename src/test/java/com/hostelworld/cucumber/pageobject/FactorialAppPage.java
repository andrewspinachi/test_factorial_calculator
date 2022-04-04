package com.hostelworld.cucumber.pageobject;

import com.hostelworld.cucumber.actions.WebDriverActions;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.hostelworld.cucumber.actions.WebDriverActions.*;

@Getter
@Component
public class FactorialAppPage extends ApplicationPage {

    @FindBy(css = "#number")
    protected WebElement numberInputSection;

    @FindBy(css = "#getFactorial")
    protected WebElement factorialButton;

    @FindBy(css = "h1.margin-base-vertical.text-center")
    protected WebElement calculatorName;

    @FindBy(xpath = "//p[@id='resultDiv']")
    protected WebElement resultMessage;

    public FactorialAppPage(WebDriver driver) {
        super(driver);
    }

    public String getMessageText() throws InterruptedException {
        Thread.sleep(300);
        return WebDriverActions.getText(resultMessage);
    }

    public Boolean checkIfPopUpIsDisplayed() {
        return isDisplayed(resultMessage);
    }

    public void inputNumber(String nr) {
        populate(numberInputSection, nr);
    }

    public boolean calulateFacorial() {
        if (isClickable(factorialButton)) {
            factorialButton.click();
        }
        return false;
    }

    public boolean isLoaded() {
        return isDisplayed(calculatorName, 4000);
    }

}
