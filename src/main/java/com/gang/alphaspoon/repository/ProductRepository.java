package com.gang.alphaspoon.repository;

import com.gang.alphaspoon.entity.Administrator;
import com.gang.alphaspoon.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value="SELECT * FROM products WHERE restaurant_id =?1 " , nativeQuery = true)
    Page<Product> findByRestaurantId(Long restaurantId, Pageable pageable);

    @Query(value="SELECT * FROM products WHERE  id=?1 AND restaurant_id=?2 ",  nativeQuery = true)
    Optional<Product> findByIdAndRestaurantId(Long id, Long restaurantId);
}
