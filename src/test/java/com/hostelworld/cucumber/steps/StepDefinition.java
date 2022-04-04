package com.hostelworld.cucumber.steps;

import com.hostelworld.cucumber.config.CucumberConfiguration;
import com.hostelworld.cucumber.pageobject.FactorialAppPage;
import com.hostelworld.ui.context.ScenarioContext;
import com.hostelworld.ui.context.SpringConfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static com.hostelworld.junit.assertions.RestCustomAssertions.assertThat;
import static com.hostelworld.ui.context.Context.PAGE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@ContextConfiguration(classes = {CucumberConfiguration.class, SpringConfig.class})
@CucumberContextConfiguration
public class StepDefinition extends BaseSteps {

    @Autowired
    private FactorialAppPage factorialAppPage;


    private static final String EFFECTIVE_URL = "http://qainterview.pythonanywhere.com/factorial";


    public StepDefinition(WebDriver driver, ScenarioContext scenarioContext,
                          CucumberConfiguration cucumberConfiguration) {
        super(driver, scenarioContext, cucumberConfiguration);
    }

    @Given("^user acceses factorial calculator webapp")
    public void factorialCalculatorExists() {
        driver.get(EFFECTIVE_URL);
        log.info("Opening URL: {}", EFFECTIVE_URL);
        assertTrue(factorialAppPage.isLoaded(), "Landing Page is loaded");
        scenarioContext.saveData(PAGE, factorialAppPage);

    }

    @When("^user fills the input sections with following value")
    public void userInputsInTheCheckboxTheNumber(String number) {
        FactorialAppPage page = (FactorialAppPage) scenarioContext.getData(PAGE);
        page.inputNumber(number);

    }

    @When("^user clicks calculate factorial button")
    public void userClicksCalculate() throws InterruptedException {

        FactorialAppPage page = (FactorialAppPage) scenarioContext.getData(PAGE);
        page.calulateFacorial();
        scenarioContext.saveData(PAGE, page);

    }

    @Then("^a result is displyed on the screen with following result")
    public void aResultIsDisplyedOnTheScreenWithFollowingMessage(String message) throws InterruptedException {
        FactorialAppPage page = (FactorialAppPage) scenarioContext.getData(PAGE);
//        assertTrue(page.checkIfPopUpIsDisplayed(), "The Pop-up with result message appear on the page");
//

        page.checkIfPopUpIsDisplayed();
        assertThat("The result is correct", page.getMessageText(), is("The factorial of 5 is: 120"));

    }

}
