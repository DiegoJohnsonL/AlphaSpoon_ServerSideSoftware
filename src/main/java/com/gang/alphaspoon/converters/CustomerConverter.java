package com.gang.alphaspoon.converters;

import com.gang.alphaspoon.entity.Customer;
import com.gang.alphaspoon.dtos.requests.SignupRequest;
import com.gang.alphaspoon.dtos.resources.CustomerResource;

public class CustomerConverter extends AbstractConverter<Customer, CustomerResource>{
    @Override
    public CustomerResource fromEntity(Customer entity) {
        return CustomerResource.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .build();
    }


    @Override
    public Customer fromDTO(CustomerResource dto) {
        return Customer.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .build();
    }

    public Customer signup(SignupRequest dto){
        return Customer.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}
