package com.gang.alphaspoon.domain.repository;

import com.gang.alphaspoon.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
