package com.glassdoor.test.intern.first.exceptions;

/**
 * Exception class used by Payer REST resource thrown when a Payer is not found for the given details.
 */
public class PayerNotFoundException extends RuntimeException {
    public PayerNotFoundException() {
        super();
    }

    public PayerNotFoundException(String message) {
        super(message);
    }

    public PayerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PayerNotFoundException(Throwable cause) {
        super(cause);
    }
}
