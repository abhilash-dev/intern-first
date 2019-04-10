package com.glassdoor.test.intern.first.controller;

import com.glassdoor.test.intern.first.helper.Constants;
import com.glassdoor.test.intern.first.model.IncomingRequest;
import com.glassdoor.test.intern.first.service.PaymentProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment/*")
public class PaymentController {

    private PaymentProcessor paymentProcessor;

    @Autowired
    public PaymentController(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    @PostMapping(value = "/process")
    public Boolean process(
            @RequestParam(value = Constants.USER_ID) int userId,
            @RequestParam(value = Constants.USER_NAME) String userName,
            @RequestParam(value = Constants.BILLING_ADDRESS) String billingAddress,
            @RequestParam(value = Constants.AMOUNT) double amount,
            @RequestParam(value = Constants.CARD_NUMBER, required = false) String cardNumber) {
        IncomingRequest incomingRequest = new IncomingRequest(userId, userName, billingAddress, amount, cardNumber);
        return paymentProcessor.processPayment(incomingRequest);
    }

    @GetMapping(value = "/test")
    public String test() {
        return "success";
    }
}