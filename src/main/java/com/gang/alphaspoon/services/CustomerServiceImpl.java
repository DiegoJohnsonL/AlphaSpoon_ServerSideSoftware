package com.gang.alphaspoon.services;

import com.gang.alphaspoon.exceptions.GeneralServiceException;
import com.gang.alphaspoon.exceptions.NoDataFoundException;
import com.gang.alphaspoon.exceptions.ResourceNotFoundException;
import com.gang.alphaspoon.domain.entity.Customer;
import com.gang.alphaspoon.domain.repository.CustomerRepository;
import com.gang.alphaspoon.domain.service.CustomerService;
import com.gang.alphaspoon.exceptions.ValidateServiceException;
import com.gang.alphaspoon.validators.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> getAllCustomer(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("User with"+
                        email + "not found"));
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer", "Id", customerId));
    }

    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        try {
            CustomerValidator.validate(customer);

            Customer existCustomer = customerRepository.findByEmail(customer.getEmail())
                    .orElse(null);
            if(existCustomer!=null)
                throw new ValidateServiceException("El correo ya esta en uso");
            return customerRepository.save(customer);
        } catch (ValidateServiceException | NoDataFoundException e){
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customerRequest) {
        return customerRepository.findById(customerId).map(customer -> {
            customer.setName(customerRequest.getName());
            customer.setPassword(customerRequest.getPassword());
            customer.setEmail(customerRequest.getEmail());
            customer.setPhoneNumber(customerRequest.getPhoneNumber());
            return customerRepository.save(customer);
        }).orElseThrow(()->new ResourceNotFoundException("Customer", "Id", customerId));
    }

    @Override
    public ResponseEntity<?> deleteCustomer(Long customerId) {
        return customerRepository.findById(customerId).map(customer -> {
            customerRepository.delete(customer);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Customer", "Id", customerId));
    }
}
