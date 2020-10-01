package com.gang.alphaspoon.users.controller;
import com.gang.alphaspoon.users.domain.model.Customer;
import com.gang.alphaspoon.users.domain.service.CustomerService;
import com.gang.alphaspoon.users.dto.resource.CustomerResource;
import com.gang.alphaspoon.users.dto.save.SaveCustomerResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ModelMapper mapper;


    @GetMapping("/customers")
    public Page<CustomerResource> getAllCustomers(Pageable pageable){
        List<CustomerResource> resources = customerService.getAllCustomer(pageable).getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable,resources.size());
    }

    @GetMapping("/customers/{customerId}")
    public CustomerResource getCustomerById(
            @PathVariable(name = "customerId")Long customerId){
        return convertToResource(customerService.getCustomerById(customerId));
    }
    @GetMapping("/customers/{customerEmail:.+}")
    public CustomerResource getCustomerById(@PathVariable(name = "customerEmail") String customerEmail){
        return convertToResource(customerService.getCustomerByEmail(customerEmail));
    }

    @PostMapping("/tags")
    public CustomerResource createCustomer(@Valid @RequestBody SaveCustomerResource customerResource){
        return convertToResource(customerService.create(convertToEntity(customerResource)));
    }

    @PutMapping("/customers/{customerId}")
    public CustomerResource updateCustomer(@PathVariable(name = "customerId") Long customerId,
                                           SaveCustomerResource customerResource){
        return convertToResource(customerService.updateCustomer(customerId, convertToEntity(customerResource)));
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "customerId") Long customerId){
        return customerService.deleteCustomer(customerId);
    }

    private Customer convertToEntity(SaveCustomerResource resource){return mapper.map(resource,Customer.class);}
    private CustomerResource convertToResource(Customer entity){return mapper.map(entity, CustomerResource.class);}
}
