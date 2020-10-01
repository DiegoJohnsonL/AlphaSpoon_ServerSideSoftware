package com.gang.alphaspoon.restaurants.domain.repository;

import com.gang.alphaspoon.restaurants.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
