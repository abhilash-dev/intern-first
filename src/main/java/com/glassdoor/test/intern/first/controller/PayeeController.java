package com.glassdoor.test.intern.first.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST resource to Create, Modify , Update & Read Payer model
 */
@RestController
@RequestMapping("payer/*")
public class PayeeController {
    // TODO Add all Payee related operations as end points here including but not limited to
    /*
     * NOTE: First thing to do is to secure all end points with Spring security so that
     *       only authorized entities can have access
     * 1. CRUD operations on Payee (Registering a payee, removing a payee, updating a payee info, fetch payee info)
     * 2. Add a payment method
     * 3. Remove a payment method
     * 4. Add a list of payment methods
     * 5. Remove a list of paymet methods
     * 6. Check if a payee with the given id exists
     * */
}
