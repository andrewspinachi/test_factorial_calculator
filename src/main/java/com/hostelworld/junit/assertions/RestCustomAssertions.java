package com.hostelworld.junit.assertions;

import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;

public class RestCustomAssertions {

    public static final String MESSAGE_EXPECTED_ACTUAL = "%s%s - [ EXPECTED]: %s%s - [ ACTUAL  ]: %s";
    public static Logger logger = LoggerFactory.getLogger(RestCustomAssertions.class);


    public static void assertThatResponseBody(String message, Response actual, String path, Matcher matcher) {
        String fullMessage = "Assert that response body " + message;
        String logMessage = String.format(MESSAGE_EXPECTED_ACTUAL, fullMessage, System.lineSeparator(), matcher.toString(), System.lineSeparator(), actual.body().path(path));
        try {
            actual.then().assertThat().body(path, matcher);
            logger.info(logMessage);
        } catch (AssertionError error) {
            logger.error(logMessage);
            logger.error(actual.getBody().prettyPrint());
            throw new AssertionError(error.getMessage());
        }
    }

    public static void assertThatStatusCodeIs(Response actual, int expectedCode) {
        String fullMessage = String.format("Assert that status code is %d ", expectedCode);
        String logMessage = String.format(MESSAGE_EXPECTED_ACTUAL, fullMessage, System.lineSeparator(), expectedCode, System.lineSeparator(), actual.statusCode());

        try {
            MatcherAssert.assertThat(fullMessage, actual.statusCode(), is(expectedCode));
            logger.info(logMessage);
        } catch (AssertionError error) {
            logger.error(logMessage);
            logger.error(actual.getBody().prettyPrint());
            throw new AssertionError(error.getMessage());
        }
    }
}
