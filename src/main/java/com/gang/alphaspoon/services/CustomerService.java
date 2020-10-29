package com.gang.alphaspoon.services;

import com.gang.alphaspoon.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    Page<Customer> getAllCustomer(Pageable pageable);
    Customer createCustomer(Customer customer);
    Customer getCustomerByEmail(String email);
    Customer getCustomerById(Long customerId);
    Customer updateCustomer(Long customerId, Customer customer);
    ResponseEntity<?> deleteCustomer(Long customerId);
}
