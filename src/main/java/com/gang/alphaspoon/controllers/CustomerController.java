package com.gang.alphaspoon.controllers;
import com.gang.alphaspoon.converters.CustomerConverter;
import com.gang.alphaspoon.entity.Customer;
import com.gang.alphaspoon.services.CustomerService;
import com.gang.alphaspoon.dtos.requests.CustomerRequest;
import com.gang.alphaspoon.dtos.requests.SignupRequest;
import com.gang.alphaspoon.dtos.resources.CustomerResource;
import com.gang.alphaspoon.utils.WrapperResponse;
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
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    CustomerConverter customerConverter;


    @GetMapping
    public Page<CustomerResource> getAllCustomers(Pageable pageable){
        List<CustomerResource> resources = customerService.getAllCustomer(pageable).getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable,resources.size());
    }

    @GetMapping("/{customerId}")
    public CustomerResource getCustomerById(
            @PathVariable(name = "customerId")Long customerId){
        return convertToResource(customerService.getCustomerById(customerId));
    }
    @GetMapping("/{customerEmail:.+}")
    public CustomerResource getCustomerByEmail(@PathVariable(name = "customerEmail") String customerEmail){
        return convertToResource(customerService.getCustomerByEmail(customerEmail));
    }

    @PostMapping
    public CustomerResource createCustomer(
            @Valid @RequestBody CustomerRequest customerResource){
        return convertToResource(customerService
                .createCustomer(convertToEntity(customerResource)));
    }

    @PostMapping("/signup")
    public ResponseEntity<WrapperResponse<CustomerResource>> signup(@RequestBody SignupRequest request){
        Customer customer = customerService.createCustomer(customerConverter.signup(request));
        return new WrapperResponse<>( true , "success", customerConverter.fromEntity(customer)).createResponse();
    }

    @PutMapping("/{customerId}")
    public CustomerResource updateCustomer(@PathVariable(name = "customerId") Long customerId,
                                           CustomerRequest customerResource){
        return convertToResource(customerService.updateCustomer(customerId, convertToEntity(customerResource)));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "customerId") Long customerId){
        return customerService.deleteCustomer(customerId);
    }

    private Customer convertToEntity(CustomerRequest resource){return mapper.map(resource,Customer.class);}
    private CustomerResource convertToResource(Customer entity){return mapper.map(entity, CustomerResource.class);}
}
