package com.gang.alphaspoon.repository;

import com.gang.alphaspoon.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value="SELECT * FROM orders WHERE customer_id =?1 " , nativeQuery = true)
    Page<Order> findAllByCustomerId(Long customerId, Pageable pageable);
    @Query(value="SELECT * FROM orders WHERE  id=?1 AND customer_id=?2 ",  nativeQuery = true)
    Optional<Order> findByIdAndCustomerId(Long id, Long customerId);
}
