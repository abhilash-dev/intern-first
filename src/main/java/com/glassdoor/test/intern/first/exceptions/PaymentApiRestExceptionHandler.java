package com.glassdoor.test.intern.first.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom Rest API Exception Handler to handle all the custom exceptions for the payment API with friendly error messages
 */
@ControllerAdvice
public class PaymentApiRestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentApiRestExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        LOG.trace("Entering handleMethodArgumentNotValid method");
        LOG.error("", ex);
        final List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final PaymentApiError paymentApiError = new PaymentApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        LOG.trace("Finishing handleMethodArgumentNotValid method");
        return handleExceptionInternal(ex, paymentApiError, headers, paymentApiError.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        LOG.trace("Entering handleBindException method");
        LOG.error("", ex);
        final List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final PaymentApiError paymentApiError = new PaymentApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        LOG.trace("Finishing handleBindException method");
        return handleExceptionInternal(ex, paymentApiError, headers, paymentApiError.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOG.trace("Entering handleTypeMismatch method");
        LOG.error("", ex);
        final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();
        final PaymentApiError paymentApiError = new PaymentApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        LOG.trace("Finishing handleTypeMismatch method");
        return new ResponseEntity<>(paymentApiError, new HttpHeaders(), paymentApiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        LOG.trace("Entering handleMissingServletRequestPart method");
        LOG.error("", ex);
        final String error = ex.getRequestPartName() + " part is missing";
        final PaymentApiError paymentApiError = new PaymentApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        LOG.trace("Finishing handleMissingServletRequestPart method");
        return new ResponseEntity<>(paymentApiError, new HttpHeaders(), paymentApiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        LOG.trace("Entering handleMissingServletRequestParameter method");
        LOG.error("", ex);
        final String error = ex.getParameterName() + " parameter is missing";
        final PaymentApiError paymentApiError = new PaymentApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        LOG.trace("Finishing handleMissingServletRequestParameter method");
        return new ResponseEntity<>(paymentApiError, new HttpHeaders(), paymentApiError.getStatus());
    }

    @ExceptionHandler({PaymentSubmissionException.class})
    public ResponseEntity<Object> handlePaymentSubmissionError(final PaymentSubmissionException ex, final WebRequest request) {
        LOG.trace("Entering handlePaymentSubmissionError method");
        LOG.error("", ex);
        final PaymentApiError paymentApiError = new PaymentApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "Error submitting payment");
        LOG.trace("Finishing handlePaymentSubmissionError method");
        return new ResponseEntity<>(paymentApiError, new HttpHeaders(), paymentApiError.getStatus());
    }

    @ExceptionHandler({InvalidIncomingRequestException.class})
    public ResponseEntity<Object> handleInvalidIncomingRequest(final InvalidIncomingRequestException ex, final WebRequest request) {
        LOG.trace("Entering handleInvalidIncomingRequest method");
        LOG.error("", ex);
        final PaymentApiError paymentApiError = new PaymentApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "Bad request");
        LOG.trace("Finishing handleInvalidIncomingRequest method");
        return new ResponseEntity<>(paymentApiError, new HttpHeaders(), paymentApiError.getStatus());
    }
}