package com.example.tvordering.controller;

import com.example.tvordering.model.Channel;
import com.example.tvordering.model.Customer;
import com.example.tvordering.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping()
    public ResponseEntity<List<Customer>> getAll() {

        List<Customer> allCustomers = customerService.getAllCustomers();

        return ResponseEntity.ok(allCustomers);
    }

    @GetMapping("/{customerId}/channel/{subscribed}")
    public ResponseEntity<List<Channel>> get(
            @PathVariable("customerId") Long customerId,
            @PathVariable("subscribed") boolean subscribed) {

        List<Channel> customerChannelsBySubscribedStatus = customerService.getCustomerChannelsBySubscribedStatus(customerId, subscribed);

        return ResponseEntity.ok(customerChannelsBySubscribedStatus);
    }


}
