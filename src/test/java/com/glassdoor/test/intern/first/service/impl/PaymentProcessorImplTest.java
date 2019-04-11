package com.glassdoor.test.intern.first.service.impl;

import com.glassdoor.test.intern.first.exceptions.InvalidIncomingRequestException;
import com.glassdoor.test.intern.first.model.IncomingRequest;
import com.glassdoor.test.intern.first.model.Payee;
import com.glassdoor.test.intern.first.model.Payer;
import com.glassdoor.test.intern.first.model.PaymentMethod;
import com.glassdoor.test.intern.first.service.PayeeService;
import com.glassdoor.test.intern.first.service.PayerService;
import com.glassdoor.test.intern.first.service.PaymentProcessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
public class PaymentProcessorImplTest {

    @Mock
    private PayeeService payeeService;

    @Mock
    private PayerService payerService;

    @InjectMocks
    private PaymentProcessor paymentProcessor = new PaymentProcessorImpl(payerService, payeeService);

    @Before
    public void setUp() {
        Set<PaymentMethod> payerPaymentMethods = new HashSet<>();
        payerPaymentMethods.add(PaymentMethod.CREDIT_CARD);
        Payer payer = new Payer(1, "payer_1", "address", payerPaymentMethods);

        Set<PaymentMethod> payeePaymentMethods = new HashSet<>();
        payeePaymentMethods.add(PaymentMethod.DEBIT_CARD);
        payeePaymentMethods.add(PaymentMethod.CREDIT_CARD);
        Payee payee = new Payee(101, "payee_1", "address", payeePaymentMethods);

        Set<PaymentMethod> payee2PaymentMethods = new HashSet<>();
        payeePaymentMethods.add(PaymentMethod.DEBIT_CARD);
        Payee payee2 = new Payee(102, "payee_2", "address", payee2PaymentMethods);

        Mockito.when(payeeService.getPayeeById(payee.getId())).thenReturn(payee);
        Mockito.when(payeeService.getPayeeById(payee2.getId())).thenReturn(payee2);

        Mockito.when(payerService.getPayerById(payer.getId())).thenReturn(payer);

    }

    @Test(expected = InvalidIncomingRequestException.class)
    public void processPayment_withInvalidPayeeId() {
        int invalidPayeeId = 999;
        int payerId = 1;
        double amount = 100d;
        String paymentMethod = "credit_card";

        IncomingRequest incomingRequest = new IncomingRequest(payerId, invalidPayeeId, amount, paymentMethod);
        paymentProcessor.processPayment(incomingRequest);
    }

    @Test(expected = InvalidIncomingRequestException.class)
    public void processPayment_withInvalidPayerId() {
        int payeeId = 101;
        int invalidPayerId = 999;
        double amount = 100d;
        String paymentMethod = "credit_card";

        IncomingRequest incomingRequest = new IncomingRequest(invalidPayerId, payeeId, amount, paymentMethod);
        paymentProcessor.processPayment(incomingRequest);
    }

    @Test(expected = InvalidIncomingRequestException.class)
    public void processPayment_withInvalidPaymentMethod() {
        int payeeId = 101;
        int payerId = 1;
        double amount = 100d;
        String invalidPaymentMethod = "gold_coins";

        IncomingRequest incomingRequest = new IncomingRequest(payerId, payeeId, amount, invalidPaymentMethod);
        paymentProcessor.processPayment(incomingRequest);
    }

    @Test(expected = InvalidIncomingRequestException.class)
    public void processPayment_withUnsupportedPayeePaymentMethod() {
        // payee2 doesn't support credit_card payments & accepts only debit_card payments
        int payeeId = 102;
        int payerId = 1;
        double amount = 100d;
        String invalidPaymentMethod = "credit_card";

        IncomingRequest incomingRequest = new IncomingRequest(payerId, payeeId, amount, invalidPaymentMethod);
        paymentProcessor.processPayment(incomingRequest);
    }
}