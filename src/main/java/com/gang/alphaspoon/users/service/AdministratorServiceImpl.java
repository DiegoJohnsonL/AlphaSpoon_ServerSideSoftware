package com.gang.alphaspoon.users.service;

import com.gang.alphaspoon.exceptions.ResourceNotFoundException;
import com.gang.alphaspoon.users.domain.model.Administrator;
import com.gang.alphaspoon.users.domain.repository.AdministratorRepository;
import com.gang.alphaspoon.users.domain.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdministratorServiceImpl implements AdministratorService
{

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public Page<Administrator> getAllAdministrators(Pageable pageable) {
        return administratorRepository.findAll(pageable);
    }


    @Override
    public Administrator getAdministratorById(Long administratorId) {
        return administratorRepository.findById(administratorId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "User with"+administratorId + "not found"));
    }

    @Override
    public Administrator getAdministratorByEmail(String email) {
        return administratorRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User with"+
                email + "not found"));
    }

    @Override
    public Administrator getAdministratorByDni(int dni) {
        return administratorRepository.findByDni(dni).orElseThrow(()->new ResourceNotFoundException("User with"+
                dni + "not found"));
    }

    @Override
    @Transactional
    public Administrator create(Administrator administrator) {
        return administratorRepository.save(administrator);
    }


    @Override
    public Administrator updateAdministrator(Long administratorId, Administrator administratorRequest) {
        return administratorRepository.findById(administratorId).map(administrator -> {
            administrator.setName(administratorRequest.getName());
            administrator.setPassword(administratorRequest.getPassword());
            administrator.setDni(administratorRequest.getDni());
            administrator.setEmail(administratorRequest.getEmail());
            administrator.setPhoneNumber(administratorRequest.getPhoneNumber());
            return administratorRepository.save(administrator);
        }).orElseThrow(()->new ResourceNotFoundException("Customer", "Id", administratorId));
    }

    @Override
    public ResponseEntity<?> deleteAdministrator(Long administratorId) {
        return administratorRepository.findById(administratorId).map(administrator -> {
            administratorRepository.delete(administrator);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Customer", "Id", administratorId));
    }
}
