package com.gang.alphaspoon.services;

import com.gang.alphaspoon.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AddressService {
    Page<Address> getAllAddress(Pageable pageable);
    Page<Address> getAllAddressByCustomerId(Long customerId, Pageable pageable);
    Address getAddressById(Long addressId);
    Address createAddress(Address address);
    ResponseEntity<?> deleteAddress(Long addressId);
}