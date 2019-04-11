package com.glassdoor.test.intern.first.service.impl;

import com.glassdoor.test.intern.first.model.Payer;
import com.glassdoor.test.intern.first.model.PaymentMethod;
import com.glassdoor.test.intern.first.model.repo.PayerRepo;
import com.glassdoor.test.intern.first.service.PayerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
public class PayerServiceImplTest {

    @Mock
    private PayerRepo payerRepo;

    @InjectMocks
    private PayerService payerService = new PayerServiceImpl(payerRepo);

    @Before
    public void setUp() {
        Set<PaymentMethod> payerPaymentMethods = new HashSet<>();
        payerPaymentMethods.add(PaymentMethod.CREDIT_CARD);
        Payer payer = new Payer(1, "payer_1", "address", payerPaymentMethods);
        Payer payer2 = new Payer(2, "payer_1", "address", payerPaymentMethods);

        Mockito.when(payerRepo.findById(payer.getId())).thenReturn(payer);
        Mockito.when(payerRepo.findByName(payer.getName())).thenReturn(Arrays.asList(payer, payer2));
        Mockito.when(payerRepo.findByIdAndName(payer.getId(), payer.getName())).thenReturn(payer);
        Mockito.when(payerRepo.exists(payer.getId())).thenReturn(true);

    }

    @Test
    public void testGetPayerById_withInvalidId() {
        int invalidPayerId = 999;

        Payer payer = payerService.getPayerById(invalidPayerId);
        Assert.assertNull(payer);
    }

    @Test
    public void testGetPayerById_withValidId() {
        int givenPayerId = 1;

        Payer payer = payerService.getPayerById(givenPayerId);
        Assert.assertEquals(payer.getId(), givenPayerId);
    }

    @Test
    public void testGetPayersByName_withNullName() {
        String nullName = null;

        List<Payer> payerList = payerService.getPayersByName(nullName);
        Assert.assertEquals(payerList.size(), 0);
    }

    @Test
    public void testGetPayerByName_withEmptyName() {
        String emptyName = "";

        List<Payer> payerList = payerService.getPayersByName(emptyName);
        Assert.assertEquals(payerList.size(), 0);
    }

    @Test
    public void testGetPayerByName_withWhiteSpaceName() {
        String whiteSpaceName = "";

        List<Payer> payerList = payerService.getPayersByName(whiteSpaceName);
        Assert.assertEquals(payerList.size(), 0);
    }

    @Test
    public void testGetPayerByName_withValidName() {
        String payerName = "payer_1";

        List<Payer> payerList = payerService.getPayersByName(payerName);
        Assert.assertEquals(payerList.size(), 2);
    }

    @Test
    public void testGetPayerByIdAndName_withInvalidIdAndInvalidName() {
        String invalidPayerName = "unknown";
        int invalidPayerId = 999;

        Payer payer = payerService.getPayerByIdAndName(invalidPayerId, invalidPayerName);
        Assert.assertNull(payer);
    }

    @Test
    public void testGetPayerByIdAndName_withInvalidIdAndValidName() {
        String validName = "payer_1";
        int invalidPayerId = 999;

        Payer payer = payerService.getPayerByIdAndName(invalidPayerId, validName);
        Assert.assertNull(payer);
    }

    @Test
    public void testGetPayerByIdAndName_withValidIdAndInvalidName() {
        String invalidPayerName = "unknown";
        int validPayerId = 1;

        Payer payer = payerService.getPayerByIdAndName(validPayerId, invalidPayerName);
        Assert.assertNull(payer);
    }

    @Test
    public void testGetPayerByIdAndName_withValidIdAndValidName() {
        String validPayerName = "payer_1";
        int validPayerId = 1;

        Payer payer = payerService.getPayerByIdAndName(validPayerId, validPayerName);
        Assert.assertEquals(payer.getId(), validPayerId);
        Assert.assertEquals(payer.getName(), validPayerName);
    }

    @Test
    public void testPayerExists_withInvalidPayer() {
        Set<PaymentMethod> payerPaymentMethods = new HashSet<>();
        payerPaymentMethods.add(PaymentMethod.CREDIT_CARD);
        Payer invalidPayer = new Payer(999, "payer_999", "address_999", payerPaymentMethods);

        Assert.assertFalse(payerService.payerExists(invalidPayer));
    }

    @Test
    public void testPayerExists_withValidPayer() {
        Set<PaymentMethod> payerPaymentMethods = new HashSet<>();
        payerPaymentMethods.add(PaymentMethod.CREDIT_CARD);
        Payer validPayer = new Payer(1, "payer_1", "address", payerPaymentMethods);

        Assert.assertTrue(payerService.payerExists(validPayer));
    }
}