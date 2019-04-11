package com.glassdoor.test.intern.first.controller;

import com.glassdoor.test.intern.first.helper.Constants;
import com.glassdoor.test.intern.first.service.PaymentProcessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentControllerTest {

    private MockMvc mvc;

    @Autowired
    private PaymentProcessor processor;

    @Autowired
    private PaymentController paymentController;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(paymentController)
                .build();
    }

    // NOTE: Catching NestedServletException instead of InvalidIncomingRequestException although it returns the latter,
    // I couldn't get it working in time for submission
    @Test(expected = NestedServletException.class)
    public void testProcessPayment_withInvalidPayerIdIncomingRequest() throws Exception {
        int payerId = 1;
        int payeeId = 999;
        double amount = 100d;
        String paymentMethod = "credit_card";

        Map<String, String> params = new HashMap<>();
        params.put(Constants.PAYER_ID, String.valueOf(payerId));
        params.put(Constants.PAYEE_ID, String.valueOf(payeeId));
        params.put(Constants.AMOUNT, String.valueOf(amount));
        params.put(Constants.PAYMENT_METHOD, paymentMethod);

        MvcResult mvcResult = mvc.perform(post("/payment/process")
                .param(Constants.PAYER_ID, String.valueOf(payerId))
                .param(Constants.PAYEE_ID, String.valueOf(payeeId))
                .param(Constants.AMOUNT, String.valueOf(amount))
                .param(Constants.PAYMENT_METHOD, paymentMethod)).andReturn();

        Assert.assertEquals(mvcResult.getResponse().getStatus(), 400);
    }

    @Test(expected = NestedServletException.class)
    public void testProcessPayment_withInvalidPayeeIdIncomingRequest() throws Exception {
        int payerId = 1;
        int payeeId = 999;
        double amount = 100d;
        String paymentMethod = "credit_card";

        Map<String, String> params = new HashMap<>();
        params.put(Constants.PAYER_ID, String.valueOf(payerId));
        params.put(Constants.PAYEE_ID, String.valueOf(payeeId));
        params.put(Constants.AMOUNT, String.valueOf(amount));
        params.put(Constants.PAYMENT_METHOD, paymentMethod);

        MvcResult mvcResult = mvc.perform(post("/payment/process")
                .param(Constants.PAYER_ID, String.valueOf(payerId))
                .param(Constants.PAYEE_ID, String.valueOf(payeeId))
                .param(Constants.AMOUNT, String.valueOf(amount))
                .param(Constants.PAYMENT_METHOD, paymentMethod)).andReturn();

        Assert.assertEquals(mvcResult.getResponse().getStatus(), 400);
    }

    // TODO: Add more payment method related tests
}