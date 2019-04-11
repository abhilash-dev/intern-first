package com.glassdoor.test.intern.first.exceptions;

/**
 * Exception class used by Payee REST resource thrown when a Payee is not found for the given details.
 */
public class PayeeNotFoundException extends RuntimeException {
    public PayeeNotFoundException() {
        super();
    }

    public PayeeNotFoundException(String message) {
        super(message);
    }

    public PayeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PayeeNotFoundException(Throwable cause) {
        super(cause);
    }
}
