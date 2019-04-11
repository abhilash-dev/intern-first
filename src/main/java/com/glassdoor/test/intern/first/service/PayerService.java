package com.glassdoor.test.intern.first.service;

import com.glassdoor.test.intern.first.model.Payer;
import com.glassdoor.test.intern.first.model.PaymentMethod;

import java.util.List;

public interface PayerService {
    Payer getPayerById(int payerId);

    List<Payer> getPayersByName(String payerName);

    Payer getPayerByIdAndName(int payerId, String payerName);

    Boolean addPaymentMethodToPayer(Payer payerToModify, PaymentMethod paymentMethod);

    Boolean removePaymentMethodFromPayer(Payer payerToModify, PaymentMethod paymentMethod);

    Boolean payerExists(Payer payer);
}
