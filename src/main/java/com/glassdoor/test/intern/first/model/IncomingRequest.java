package com.glassdoor.test.intern.first.model;

import com.glassdoor.test.intern.first.exceptions.InvalidIncomingRequestException;

/**
 * Model class that encapsulate required information for a specific payment transaction
 */
public class IncomingRequest {
    private int payerId;
    private int payeeId;
    private double amount;
    private PaymentMethod paymentMethod;

    public IncomingRequest(int payerId, int payeeId, double amount, String paymentMethod) {
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.amount = amount;
        try {
            this.paymentMethod = PaymentMethod.valueOf(paymentMethod.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidIncomingRequestException("BAD Request: Invalid payment type");
        }
    }

    public IncomingRequest(int payerId, int payeeId, double amount, PaymentMethod paymentMethod) {
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public int getPayerId() {
        return payerId;
    }

    public int getPayeeId() {
        return payeeId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
}