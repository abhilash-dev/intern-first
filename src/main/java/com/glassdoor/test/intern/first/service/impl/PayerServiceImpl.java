package com.glassdoor.test.intern.first.service.impl;

import com.glassdoor.test.intern.first.model.Payer;
import com.glassdoor.test.intern.first.model.PaymentMethod;
import com.glassdoor.test.intern.first.model.repo.PayerRepo;
import com.glassdoor.test.intern.first.service.PayerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Service Layer Class that provides an implementation for the PayerService
 */
@Service
public class PayerServiceImpl implements PayerService {

    private PayerRepo payerRepo;

    @Autowired
    public PayerServiceImpl(PayerRepo payerRepo) {
        this.payerRepo = payerRepo;
    }


    @Override
    public Payer getPayerById(int payerId) {
        return payerRepo.findById(payerId);
    }

    @Override
    public List<Payer> getPayersByName(String payerName) {
        if (StringUtils.isBlank(payerName)) {
            return Collections.emptyList();
        }

        return payerRepo.findByName(payerName);
    }

    @Override
    public Payer getPayerByIdAndName(int payerId, String payerName) {
        if (StringUtils.isBlank(payerName)) {
            return null;
        }

        return payerRepo.findByIdAndName(payerId, payerName);
    }

    @Override
    public Boolean addPaymentMethodToPayer(Payer payerToModify, PaymentMethod paymentMethod) {
        // TODO: Implement a method add a payment method to an already existing list of payment methods
    /*
        Things to consider:
        1.  Validate if the payment method is a valid one (supported by the system)
        2.  Check to ensure if the payment method is not already in the payment list

     */

        return false;
    }

    @Override
    public Boolean removePaymentMethodFromPayer(Payer payerToModify, PaymentMethod paymentMethod) {

        // TODO: Implement a method to remove a payment method from existing list of payment methods
    /*
        Things to consider:
        1.  Validate if the payment method is a valid one (supported by the system)
        2.  Check to ensure if the payment method is indeed associated with the payer
        3.  Depending on the requirements specification, throw an error if it is required for a payer
            to have at least one type of payment method to be associated with the account

     */
        return false;
    }

    @Override
    public Boolean payerExists(Payer payer) {
        if (payer == null) {
            return false;
        }
        return payerRepo.exists(payer.getId());
    }
}