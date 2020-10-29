package com.gang.alphaspoon.repository;

import com.gang.alphaspoon.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
