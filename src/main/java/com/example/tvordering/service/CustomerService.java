package com.example.tvordering.service;

import com.example.tvordering.model.Channel;
import com.example.tvordering.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer getCustomerById(Long customerId);

    List<Customer> getAllCustomers();

    List<Channel> getCustomerChannelsBySubscribedStatus(Long customerId, boolean subscribed);

}
