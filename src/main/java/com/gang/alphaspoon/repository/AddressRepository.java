package com.gang.alphaspoon.repository;


import com.gang.alphaspoon.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}