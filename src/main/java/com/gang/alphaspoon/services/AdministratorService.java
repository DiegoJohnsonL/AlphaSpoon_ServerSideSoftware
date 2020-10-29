package com.gang.alphaspoon.services;

import com.gang.alphaspoon.entity.Administrator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AdministratorService {
    Page<Administrator> getAllAdministratorsByRestaurantId(Long restaurantId, Pageable pageable);
    Administrator getAdministratorByEmail(String email);
    Administrator getAdministratorByDni(Integer dni);
    Administrator getAdministratorByIdAndRestaurantId(Long administratorId, Long restaurantId);
    Administrator createAdministrator(Long restaurantId, Administrator administrator);
    Administrator updateAdministrator(Long restaurantId, Long administratorId, Administrator administrator);
    ResponseEntity<?> deleteAdministrator(Long restaurantId, Long administratorId);
}
