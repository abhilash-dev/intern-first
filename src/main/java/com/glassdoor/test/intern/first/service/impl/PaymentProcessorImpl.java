package com.glassdoor.test.intern.first.service.impl;

import com.glassdoor.test.intern.first.exceptions.InvalidIncomingRequestException;
import com.glassdoor.test.intern.first.exceptions.PaymentSubmissionException;
import com.glassdoor.test.intern.first.model.IncomingRequest;
import com.glassdoor.test.intern.first.model.Payee;
import com.glassdoor.test.intern.first.model.Payer;
import com.glassdoor.test.intern.first.model.PaymentMethod;
import com.glassdoor.test.intern.first.service.PayeeService;
import com.glassdoor.test.intern.first.service.PayerService;
import com.glassdoor.test.intern.first.service.PaymentProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Service Layer Class that provides an implementation for the PaymentProcessor,
 * This class is only repsponsible to delegate payment processing to Other PaymentHandlers
 * (Such as CreditCardPaymentHandler, DebitCardPaymentHandler etc) although this feature is not yet implemented
 */
@Service
public class PaymentProcessorImpl implements PaymentProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(PaymentProcessorImpl.class);

    private PayerService payerService;
    private PayeeService payeeService;

    @Autowired
    public PaymentProcessorImpl(PayerService payerService, PayeeService payeeService) {
        this.payerService = payerService;
        this.payeeService = payeeService;
    }

    @Override
    public void processPayment(IncomingRequest incomingRequest) {
        LOG.trace("Entering processPayment method");
        Payer payer = getPayer(incomingRequest);
        Payee payee = getPayee(incomingRequest);
        if (validIncomingRequest(payer, payee, incomingRequest)) {
            try {
                submitPayment(payer, payee, incomingRequest.getPaymentMethod(), incomingRequest.getAmount());
                LOG.info("payment for {} has been submitted", incomingRequest.getPayerId());
                LOG.trace("Finishing processPayment method");
            } catch (Exception e) {
                LOG.error("There was an error submitting the payment");
                LOG.trace("Finishing processPayment method");
                throw new PaymentSubmissionException("There was an error submitting the payment", e);
            }
        }
        LOG.trace("Finishing processPayment method");
    }

    private Payee getPayee(IncomingRequest incomingRequest) {
        return payeeService.getPayeeById(incomingRequest.getPayeeId());
    }

    private Payer getPayer(IncomingRequest incomingRequest) {
        return payerService.getPayerById(incomingRequest.getPayerId());
    }

    private boolean validIncomingRequest(Payer payer, Payee payee, IncomingRequest incomingRequest) {
        LOG.trace("Entering validIncomingRequest method");
        if (payer != null) {
            if (payee != null) {
                if (isValidPaymentMethodForPayee(payee, incomingRequest.getPaymentMethod())) {
                    LOG.trace("Finishing validIncomingRequest method");
                    return true;
                } else {
                    LOG.debug("BAD REQUEST: Invalid Payment method for the payer with following details\n PayerId : {}, PayeeId: {}, PaymentMethod: {}", incomingRequest.getPayerId(), incomingRequest.getPayeeId(), incomingRequest.getPaymentMethod());
                    LOG.error("BAD REQUEST: Invalid Payment method");
                    throw new InvalidIncomingRequestException("BAD REQUEST: Invalid payment method. Payee - " + payee.getName() + " doesn't accept " + incomingRequest.getPaymentMethod() + " payment method");
                }
            } else {
                LOG.debug("BAD REQUEST: Invalid Payee, No details for the payee with PayeeId : {}", incomingRequest.getPayeeId());
                LOG.error("BAD REQUEST: Invalid Payee");
                throw new InvalidIncomingRequestException("BAD REQUEST: Invalid Payee. No records found for " + incomingRequest.getPayeeId());
            }
        } else {
            LOG.debug("BAD REQUEST: Invalid Payer, No details for the payer with PayerId : {}", incomingRequest.getPayerId());
            LOG.error("BAD REQUEST: Invalid Payer");
            throw new InvalidIncomingRequestException("BAD REQUEST: Invalid Payer. No records found for " + incomingRequest.getPayerId());
        }
    }

    private boolean isValidPaymentMethodForPayee(Payee payee, PaymentMethod paymentMethod) {
        LOG.trace("Entering isValidPaymentMethodForPayee method");
        Set<PaymentMethod> supportedPaymentMethods = payee.getPaymentMethods();
        return supportedPaymentMethods.contains(paymentMethod);
    }

    /**
     * Make use of custom payment handlers (such as CreditCardPaymentHandler, DebitCardPaymentHandler etc.,) to
     * delegate payment submission
     *
     * @param payer
     * @param payee
     * @param paymentMethod
     * @param amount
     */
    private void submitPayment(Payer payer, Payee payee, PaymentMethod paymentMethod, double amount) {
        LOG.trace("Entering submitPayment method");
        //Don't implement this.
        LOG.trace("Finishing submitPayment method");
    }
}