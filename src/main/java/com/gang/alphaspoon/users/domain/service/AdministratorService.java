package com.gang.alphaspoon.users.domain.service;

import com.gang.alphaspoon.users.domain.model.Administrator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AdministratorService {
    Page<Administrator> getAllAdministrators(Pageable pageable);
    Administrator create(Administrator administrator);
    Administrator getAdministratorByEmail(String email);
    Administrator getAdministratorByDni(int dni);
    Administrator getAdministratorById(Long administratorId);
    Administrator updateAdministrator(Long administratorId, Administrator administrator);
    ResponseEntity<?> deleteAdministrator(Long administratorId);
}
