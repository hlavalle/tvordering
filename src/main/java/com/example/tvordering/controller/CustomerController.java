package com.example.tvordering.controller;

import com.example.tvordering.model.Customer;
import com.example.tvordering.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> get(@PathVariable("customerId") Long customerId) {

        Customer customerById = customerService.getCustomerById(customerId);

        return ResponseEntity.ok(customerById);

    }


}
