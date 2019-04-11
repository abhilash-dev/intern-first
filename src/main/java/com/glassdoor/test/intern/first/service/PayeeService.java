package com.glassdoor.test.intern.first.service;

import com.glassdoor.test.intern.first.model.Payee;
import com.glassdoor.test.intern.first.model.PaymentMethod;

import java.util.List;

public interface PayeeService {

    Payee getPayeeById(int payeeId);

    List<Payee> getPayeesByName(String payeeName);

    Payee getPayeeByIdAndName(int payeeId, String payeeName);

    Boolean addPaymentMethodToPayee(Payee payeeToModify, PaymentMethod paymentMethod);

    Boolean removePaymentMethodFromPayee(Payee payeeToModify, PaymentMethod paymentMethod);

    Boolean payeeExists(Payee payee);
}