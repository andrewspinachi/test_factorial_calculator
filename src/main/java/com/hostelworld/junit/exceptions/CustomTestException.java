package com.hostelworld.junit.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomTestException extends RuntimeException {

    public CustomTestException() {
        super();
    }

    public CustomTestException(String message) {
        super(message);
        log.error("Exception occurred: {}", message);
    }

    public CustomTestException(String message, String error) {
        super(message);
    }
}