package com.glassdoor.test.intern.first.service.impl;

import com.glassdoor.test.intern.first.model.Payee;
import com.glassdoor.test.intern.first.model.PaymentMethod;
import com.glassdoor.test.intern.first.model.repo.PayeeRepo;
import com.glassdoor.test.intern.first.service.PayeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Service Layer Class that provides an implementation for the PayeeService
 */
@Service
public class PayeeServiceImpl implements PayeeService {

    private PayeeRepo payeeRepo;

    @Autowired
    public PayeeServiceImpl(PayeeRepo payeeRepo) {
        this.payeeRepo = payeeRepo;
    }


    @Override
    public Payee getPayeeById(int payeeId) {
        return payeeRepo.findById(payeeId);
    }

    @Override
    public List<Payee> getPayeesByName(String payeeName) {

        if (StringUtils.isBlank(payeeName)) {
            return Collections.emptyList();
        }

        return payeeRepo.findByName(payeeName);
    }

    @Override
    public Payee getPayeeByIdAndName(int payeeId, String payeeName) {
        if (StringUtils.isBlank(payeeName)) {
            return null;
        }

        return payeeRepo.findByIdAndName(payeeId, payeeName);
    }

    @Override
    public Boolean payeeExists(Payee payee) {
        if (payee == null) {
            return false;
        }
        return payeeRepo.exists(payee.getId());
    }

    @Override
    public Boolean addPaymentMethodToPayee(Payee payeeToModify, PaymentMethod paymentMethod) {
        if (payeeExists(payeeToModify)) {
            // TODO: Implement a method add a payment method to an already existing list of payment methods
    /*
        Things to consider:
        1.  Validate if the payment method is a valid one (supported by the system)
        2.  Check to ensure if the payment method is not already in the payment list

     */
        }
        return false;
    }

    @Override
    public Boolean removePaymentMethodFromPayee(Payee payeeToModify, PaymentMethod paymentMethod) {
        // TODO: Implement a method to remove a payment method from existing list of payment methods
    /*
        Things to consider:
        1.  Validate if the payment method is a valid one (supported by the system)
        2.  Check to ensure if the payment method is indeed associated with the payee
        3.  Depending on the requirements specification, throw an error if it is required for a payee
            to have at least one type of payment method to be associated with the account

     */
        return false;
    }
}