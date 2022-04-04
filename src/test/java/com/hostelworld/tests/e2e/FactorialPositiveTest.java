package com.hostelworld.tests.e2e;

import com.hostelworld.BaseFactorialTest;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.hostelworld.junit.assertions.RestCustomAssertions.*;
import static com.hostelworld.junit.utils.FileUtils.readResource;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;


public class FactorialPositiveTest extends BaseFactorialTest {

    @ParameterizedTest(name = "Test factorial with input value {0} and outputs a correct value")
    @CsvSource({"1,1", "2,2", "3,6", "4,24"})
    void factorialTest200(String useCase, String result) {

        Response response = given()
                .body(format(readResource(REQUEST_PATH_TXT), useCase))
                .contentType(CONTENT_TYPE)
                .post(BASE_ENDPOINT);

        Integer results = Integer.valueOf(result);

        assertAll( // check the advantages of assert all
                () -> assertThatStatusCodeIs(response, SC_OK),
                () -> assertThatResponseBody("contains result", response, "answer", is(results))
        );

    }

    @DisplayName("Calculate factorial of large number: 1000")
    @Test
    @Disabled("Generates 500 Internal Server Error")
    public void fatorialOfLargeNumberTest200() {

        Response response = given()
                .body(format(readResource(REQUEST_PATH_TXT), 1000))
                .contentType(CONTENT_TYPE)
                .post(BASE_ENDPOINT);

        assertAll( // check the advantages of assert all
                () -> assertThatStatusCodeIs(response, SC_OK),
                () -> assertThatResponseBody("contains result", response, "answer", is("4.0238726 E+2567"))
        );

    }

    @DisplayName("Calculate factorial of large number: 500")
    @Test
    public void factorialOfNumber500Test() {

        Response response = given()
                .body(format(readResource(REQUEST_PATH_TXT), 500))
                .contentType(CONTENT_TYPE)
                .post(BASE_ENDPOINT);

        assertAll( // check the advantages of assert all
                () -> assertThatStatusCodeIs(response, SC_OK),
                () -> assertThatResponseBody("contains result", response, "answer", is(notNullValue()))
        );

    }

    @DisplayName("Find max number which factorial calculator can process")
    @Test
    public void findTheMaxNumberTest() {

        int number = 900;

        while (number < 1000) {
            Response response = given()
                    .body("number= " + number)
                    .contentType(CONTENT_TYPE)
                    .post(BASE_ENDPOINT);
            number++;

            if (response.getStatusCode() == 500) {
                logger.info("Display the max number the calculator can process: {}", number);
                break;

            } else
                assertThatStatusCodeIs(response, SC_OK);
                logger.info("Test passed with next input: {}", number);
        }

    }

}