package com.glassdoor.test.intern.first.controller;

import com.glassdoor.test.intern.first.helper.Constants;
import com.glassdoor.test.intern.first.model.IncomingRequest;
import com.glassdoor.test.intern.first.service.PaymentProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST resource to mediate the payment process between the models Payer & Payee
 */
@RestController
@RequestMapping("/payment/*")
public class PaymentController {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);

    private PaymentProcessor paymentProcessor;

    @Autowired
    public PaymentController(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    // TODO secure the endpoints with spring-security so that only authorized entities can gain access

    // TODO document the Payment REST API using swagger notations

    @PostMapping(value = "/process")
    public @ResponseBody
    ResponseEntity process(
            @RequestParam(value = Constants.PAYER_ID) int payerId,
            @RequestParam(value = Constants.PAYEE_ID) int payeeId,
            @RequestParam(value = Constants.AMOUNT) double amount,
            @RequestParam(value = Constants.PAYMENT_METHOD) String paymentMethod) {
        LOG.trace("Entering process method");
        IncomingRequest incomingRequest = new IncomingRequest(payerId, payeeId, amount, paymentMethod);
        paymentProcessor.processPayment(incomingRequest);
        LOG.trace("Finishing process method");
        return new ResponseEntity("Payment Successfull", HttpStatus.OK);
    }
}