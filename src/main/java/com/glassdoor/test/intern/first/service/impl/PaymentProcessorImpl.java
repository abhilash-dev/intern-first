package com.glassdoor.test.intern.first.service.impl;

import com.glassdoor.test.intern.first.helper.UserDatabase;
import com.glassdoor.test.intern.first.model.IncomingRequest;
import com.glassdoor.test.intern.first.service.PaymentProcessor;
import org.springframework.stereotype.Service;

@Service
public class PaymentProcessorImpl implements PaymentProcessor {

    @Override
    public boolean processPayment(IncomingRequest incomingRequest) {
        UserDatabase userDatabase = new UserDatabase();
        if (userDatabase.userNames.containsKey(incomingRequest.getUserId())) {
            if (incomingRequest.getUserName().equals(userDatabase.userNames.get(incomingRequest.getUserId()))
                    && validateAddress(incomingRequest.getBillingAddress(), userDatabase.addresses.get(incomingRequest.getUserId()))) {
                try {
                    submitPayment(incomingRequest.getCardnumber(), incomingRequest.getAmount());
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }

    private void submitPayment(String card, double amount) {
        //Don't implement this.
    }

    private boolean validateAddress(String addressFromRequest, String addressFromDatabase) {
        return addressFromRequest.equals(addressFromDatabase);
    }
}
