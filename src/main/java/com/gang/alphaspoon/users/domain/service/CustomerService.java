package com.gang.alphaspoon.users.domain.service;

import com.gang.alphaspoon.users.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    Page<Customer> getAllCustomer(Pageable pageable);
    Customer create(Customer customer);
    Customer getCustomerByEmail(String email);
    Customer getCustomerById(Long customerId);
    Customer updateCustomer(Long customerId, Customer customer);
    ResponseEntity<?> deleteCustomer(Long customerId);
}
