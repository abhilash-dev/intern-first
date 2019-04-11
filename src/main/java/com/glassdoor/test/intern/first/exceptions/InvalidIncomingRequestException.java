package com.glassdoor.test.intern.first.exceptions;

/**
 * Exception class thrown for all invalid cases of IncomingRequest model
 */
public class InvalidIncomingRequestException extends RuntimeException {
    public InvalidIncomingRequestException() {
        super();
    }

    public InvalidIncomingRequestException(String message) {
        super(message);
    }

    public InvalidIncomingRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidIncomingRequestException(Throwable cause) {
        super(cause);
    }
}
