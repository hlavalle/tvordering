package com.example.tvordering.service.impl;

import com.example.tvordering.model.Customer;
import com.example.tvordering.repository.CustomerRepository;
import com.example.tvordering.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Customer getCustomerById(Long customerId) {
        Optional<Customer> optCustomer = customerRepository.findById(customerId);
        return optCustomer.get();
    }
}
