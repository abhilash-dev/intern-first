package com.glassdoor.test.intern.first.helper;

import com.glassdoor.test.intern.first.model.Payee;
import com.glassdoor.test.intern.first.model.Payer;
import com.glassdoor.test.intern.first.model.PaymentMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Helper class that reads data from a text file & parses it to its respective entity types
 */
public class DataLoaderHelper {
    private static final Logger LOG = LoggerFactory.getLogger(DataLoaderHelper.class);

    private DataLoaderHelper() {
        // Empty private constructor to prevent instantiation
    }

    public static List<Payer> getPayersInfo() {
        LOG.trace("Entering getPayersInfo method");
        List<Payer> payers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                DataLoaderHelper.class.getClassLoader().getResourceAsStream(Constants.PAYER_INFO_FILENAME)))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] splits = line.split("\t");
                String[] payMethods = splits[3].split(",");
                Set<PaymentMethod> paymentMethods = new HashSet<>();
                PaymentMethod paymentMethod;
                for (String paymentMethodStr : payMethods) {
                    paymentMethod = PaymentMethod.valueOf(paymentMethodStr);
                    paymentMethods.add(paymentMethod);
                }
                payers.add(new Payer(Integer.parseInt(splits[0]), splits[1], splits[2], paymentMethods));
            }
            LOG.trace("Finishing getPayersInfo method");
            return payers;
        } catch (IOException e) {
            LOG.error("", e);
        }
        LOG.trace("Finishing getPayersInfo method");
        return Collections.emptyList();
    }

    public static List<Payee> getPayeesInfo() {
        LOG.trace("Entering getPayeesInfo method");
        List<Payee> payees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                DataLoaderHelper.class.getClassLoader().getResourceAsStream(Constants.PAYEE_INFO_FILENAME)))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] splits = line.split("\t");
                String[] payMethods = splits[3].split(",");
                Set<PaymentMethod> paymentMethods = new HashSet<>();
                PaymentMethod paymentMethod;
                for (String paymentMethodStr : payMethods) {
                    paymentMethod = PaymentMethod.valueOf(paymentMethodStr);
                    paymentMethods.add(paymentMethod);
                }
                payees.add(new Payee(Integer.parseInt(splits[0]), splits[1], splits[2], paymentMethods));
            }
            LOG.trace("Finishing getPayeesInfo method");
            return payees;
        } catch (IOException e) {
            LOG.error("", e);
        }
        LOG.trace("Finishing getPayeesInfo method");
        return Collections.emptyList();
    }
}