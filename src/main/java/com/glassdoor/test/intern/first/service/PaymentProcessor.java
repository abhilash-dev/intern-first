package com.glassdoor.test.intern.first.service;

import com.glassdoor.test.intern.first.model.IncomingRequest;

public interface PaymentProcessor {
    void processPayment(IncomingRequest incomingRequest);
}
