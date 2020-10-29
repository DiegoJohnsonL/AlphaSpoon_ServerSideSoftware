package com.gang.alphaspoon.repository;
import com.gang.alphaspoon.entity.Administrator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    @Query(value="SELECT * FROM administrators WHERE restaurant_id =?1 " ,
            nativeQuery = true)
    Page<Administrator> findByRestaurantId(Long restaurantId, Pageable pageable);

    @Query(value="SELECT * FROM administrators WHERE  id=?1 AND restaurant_id=?2 ",
            nativeQuery = true)
    Optional<Administrator> findByIdAndRestaurantId(Long id, Long restaurantId);

    Optional<Administrator> findByEmail(String email);
    Optional<Administrator> findByDni(Integer dni);

}
