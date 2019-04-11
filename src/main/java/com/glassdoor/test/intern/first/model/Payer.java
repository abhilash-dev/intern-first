package com.glassdoor.test.intern.first.model;

import org.springframework.data.annotation.Id;

import java.util.Collections;
import java.util.Set;

/**
 * Model class that encapsulate all the basic details about a Payer entity
 */
public class Payer {
    @Id
    private int id;
    private String name;
    private String address;
    private Set<PaymentMethod> paymentMethods;

    public Payer() {
        this.paymentMethods = Collections.emptySet();
    }

    public Payer(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.paymentMethods = Collections.emptySet();
    }

    public Payer(int id, String name, String address, Set<PaymentMethod> paymentMethods) {
        this.id = id;
        this.name = name;
        this.address = address;
        if (paymentMethods != null) {
            this.paymentMethods = paymentMethods;
        } else {
            this.paymentMethods = Collections.emptySet();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(Set<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    // TODO: Implement a method add a payment method to an already existing list of payment methods
    /*
        Things to consider:
        1.  Validate if the payment method is a valid one (supported by the system)
        2.  Check to ensure if the payment method is not already in the payment list

     */


    // TODO: Implement a method to remove a payment method from existing list of payment methods
    /*
        Things to consider:
        1.  Validate if the payment method is a valid one (supported by the system)
        2.  Check to ensure if the payment method is indeed associated with the payer
        3.  Depending on the requirements specification, throw an error if it is required for a payer
            to have at least one type of payment method to be associated with the account

     */

    @Override
    public String toString() {
        return "Payer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}