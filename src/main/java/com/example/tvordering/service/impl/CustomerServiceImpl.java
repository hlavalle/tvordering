package com.example.tvordering.service.impl;

import com.example.tvordering.exceptions.TvorderingNotFoundException;
import com.example.tvordering.model.Channel;
import com.example.tvordering.model.Customer;
import com.example.tvordering.repository.CustomerChannelRepository;
import com.example.tvordering.repository.CustomerRepository;
import com.example.tvordering.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerChannelRepository customerChannelRepository;

    @Override
    public Customer getCustomerById(Long customerId) {
        Optional<Customer> optCustomer = customerRepository.findById(customerId);
        return optCustomer.orElseThrow(()-> new TvorderingNotFoundException("Customer "+customerId+" not found."));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Channel> getCustomerChannelsBySubscribedStatus(Long customerId, boolean subscribed) {
        return customerChannelRepository.findUserChannelsBySubscribedStatus(customerId, subscribed);
    }

    @Override
    public List<Channel> getAllCustomerChannels(Long customerId) {
        return customerChannelRepository.findAllUserChannels(customerId);
    }
}
