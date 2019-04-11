package com.glassdoor.test.intern.first.exceptions;

/**
 * Exception class used for errors in payment submission
 */
public class PaymentSubmissionException extends RuntimeException {
    public PaymentSubmissionException() {
        super();
    }

    public PaymentSubmissionException(String message) {
        super(message);
    }

    public PaymentSubmissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentSubmissionException(Throwable cause) {
        super(cause);
    }
}
