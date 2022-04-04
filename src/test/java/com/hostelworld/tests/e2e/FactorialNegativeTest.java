package com.hostelworld.tests.e2e;

import com.hostelworld.tests.BaseFactorialTest;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.hostelworld.junit.assertions.RestCustomAssertions.assertThatStatusCodeIs;
import static com.hostelworld.junit.utils.FileUtils.readResource;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FactorialNegativeTest extends BaseFactorialTest {

    @ParameterizedTest(name = "Test factorial calculator with invalid data {0} ")
    @CsvSource({"a", ".+-", "0-0"})
    public void factorialTest500(String useCase) {

        Response response = given()
                .body(format(readResource(REQUEST_PATH_TXT), useCase))
                .contentType(CONTENT_TYPE)
                .post(BASE_ENDPOINT);

        assertAll( // check the advantages of assert all
                () -> assertThatStatusCodeIs(response, SC_INTERNAL_SERVER_ERROR)
        );
    }

    @ParameterizedTest(name = "Test factorial with valid data with spaces {0}")
    @CsvSource({"5   ", "    5", "  33  "})
    @Disabled("Potential Bug - most probably spaces are ignored when sending request with sample html body text")
    public void factorialInputWithSpacesTest500(String useCase) {

        Response response = given()
                .body(format(readResource(REQUEST_PATH_TXT), useCase))
                .contentType(CONTENT_TYPE)
                .post(BASE_ENDPOINT);

        assertThatStatusCodeIs(response, SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("Send request as JSON - 500 Internal Server Error")
    public void factorialTest500() {

        Response response = given()
                .body(format(readResource(REQUEST_PATH_JSON), "5"))
                .contentType(CONTENT_TYPE)
                .post(INVALID_ENDPOINT);

        assertThatStatusCodeIs(response, SC_INTERNAL_SERVER_ERROR);


    }

    @DisplayName("PUT method not allowed")
    @Test
    public void factorialTest405() {

        Response response = given()
                .body("number=2")
                .contentType(CONTENT_TYPE)
                .put(BASE_ENDPOINT);

        assertThatStatusCodeIs(response, SC_METHOD_NOT_ALLOWED);

    }

    @Disabled // Fails with Internal Server Error - 500 - it could be a potential defect
    @DisplayName("Send Invalid request body - 400 Bad Request")
    @Test
    public void factorialTest400() {

        Response response = given()
                .body("InvalidRequestBody")
                .contentType(CONTENT_TYPE)
                .post(BASE_ENDPOINT);

        assertThatStatusCodeIs(response, SC_BAD_REQUEST);

    }

}
