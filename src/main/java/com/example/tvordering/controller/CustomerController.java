package com.example.tvordering.controller;

import com.example.tvordering.model.Channel;
import com.example.tvordering.model.Customer;
import com.example.tvordering.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Api(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}")
    @ApiOperation(value = "Retrieve the customer information based on the customer id")
    public ResponseEntity<Customer> get(@PathVariable("customerId") Long customerId) {

        Customer customerById = customerService.getCustomerById(customerId);

        return ResponseEntity.ok(customerById);
    }

    @GetMapping()
    @ApiOperation(value = "Retrieve information of all customers")
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> allCustomers = customerService.getAllCustomers();
        return ResponseEntity.ok(allCustomers);
    }

    @GetMapping("/{customerId}/channel")
    @ApiOperation(value = "Retrieve customer channels")
    public ResponseEntity<List<Channel>> get(
            @PathVariable("customerId") Long customerId,
            @RequestParam(value = "subscribed", required = false) Boolean subscribed) {

        customerService.getCustomerById(customerId);

        List<Channel> customerChannels;
        if (null == subscribed) {
            customerChannels = customerService.getAllCustomerChannels(customerId);
        }
        else {
            customerChannels = customerService.getCustomerChannelsBySubscribedStatus(customerId, subscribed);
        }

        return ResponseEntity.ok(customerChannels);
    }


}
