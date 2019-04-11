package com.glassdoor.test.intern.first.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST resource to Create, Modify , Update & Read Payer model
 */
@RestController
@RequestMapping("payer/*")
public class PayerController {
    // TODO Add all Payer related operations as end points here including but not limited to
    /*
     * NOTE: First thing to do is to secure all end points with Spring security so that
     *       only authorized entities can have access
     * 1. CRUD operations on Payer (Registering a payer, removing a payer, updating a payer info, fetch payer info)
     * 2. Add a payment method
     * 3. Remove a payment method
     * 4. Add a list of payment methods
     * 5. Remove a list of paymet methods
     * 6. Check if a payee with the given id exists
     * */
}
