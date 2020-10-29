package com.gang.alphaspoon.services.Impl;

import com.gang.alphaspoon.entity.Address;
import com.gang.alphaspoon.exceptions.GeneralServiceException;
import com.gang.alphaspoon.exceptions.NoDataFoundException;
import com.gang.alphaspoon.exceptions.ValidateServiceException;
import com.gang.alphaspoon.repository.AddressRepository;
import com.gang.alphaspoon.repository.CustomerRepository;
import com.gang.alphaspoon.services.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Address> getAllAddress(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    //TODO
    @Override
    public Page<Address> getAllAddressByCustomerId(Long customerId, Pageable pageable) {
        return null;
    }

    @Override
    public Address getAddressById(Long addressId) {
        try {
            return addressRepository.findById(addressId)
                    .orElseThrow(() -> new NoDataFoundException("La dirección no existe"));
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public ResponseEntity<?> deleteAddress(Long addressId) {
        return addressRepository.findById(addressId).map(address -> {
            addressRepository.delete(address);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new NoDataFoundException("addres with"+ addressId + "not found"  ));
    }
}
