package com.gang.alphaspoon.domain.repository;
import com.gang.alphaspoon.domain.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    Optional<Administrator> findByEmail(String email);
    Optional<Administrator> findByDni(int dni);
}
