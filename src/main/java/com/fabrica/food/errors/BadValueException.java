package com.fabrica.food.errors;

public class BadValueException extends RuntimeException {
    public BadValueException(String message) {super(message);};
    public BadValueException(String message, Throwable cause) {super(message, cause);};
}
