package com.hostelworld.junit.assertions;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;

@Slf4j
public class RestCustomAssertions {

    protected static final String ASSERT_THAT = "Assert that ";
    protected static final String ASSERT_EXPECTED_ACTUAL_MESSAGE_FORMAT = "%s%4$s - [ EXPECTED]: %s%4$s - [ ACTUAL  ]: %s";

    public static final String MESSAGE_EXPECTED_ACTUAL = "%s%s - [ EXPECTED]: %s%s - [ ACTUAL  ]: %s";
    public static Logger logger = LoggerFactory.getLogger(RestCustomAssertions.class);

    protected static String getAssertExpectedActualMessage(final String message, final String expected, final String actual) {
        return String.format(ASSERT_EXPECTED_ACTUAL_MESSAGE_FORMAT, message, expected, actual, System.lineSeparator());
    }

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

    public static <T> void assertThat(final String message, final T actual, final Matcher<? super T> matcher) {
        final String fullMessage = ASSERT_THAT + message;
        final String logMessage = getAssertExpectedActualMessage(fullMessage, String.valueOf(matcher), String.valueOf(actual));

        try {
            MatcherAssert.assertThat(fullMessage, actual, matcher);
            log.info(logMessage);
        } catch (AssertionError e) {
            log.error(logMessage);
            throw e;
        }
    }
}
