package com.hostelworld.junit.exceptions;

public class CustomTestException extends RuntimeException {

    public CustomTestException() {
        super();
    }

    public CustomTestException(String message) {
        super(message);
    }

    public CustomTestException(String message, String error) {
        super(message);
    }
}