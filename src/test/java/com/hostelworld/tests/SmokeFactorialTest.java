package com.hostelworld.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.hostelworld.junit.assertions.RestCustomAssertions.logger;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.POST;
import static org.hamcrest.Matchers.equalTo;

public class SmokeFactorialTest extends BaseFactorialTest {

    private static final String REQUEST_BODY = "number=5";

    @Test
    @DisplayName("Test GET method")
    public void getFactorialCalculator() {

        logger.info("Start GET factorial calculator test!");

        when()
                .request(GET, BASE_ENDPOINT)
                .then()
                .statusCode(200);

    }

    @Test
    @DisplayName("Test POST method")
    public void postFactorialCalculator() {

        logger.info("Start the test!");

        with().body(REQUEST_BODY).contentType(CONTENT_TYPE)
                .when()
                .request(POST, BASE_ENDPOINT)
                .then()
                .statusCode(200)
                .and()
                .assertThat().body("answer", equalTo(120));

    }
}