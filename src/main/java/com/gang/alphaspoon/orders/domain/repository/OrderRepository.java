package com.gang.alphaspoon.orders.domain.repository;

import com.gang.alphaspoon.orders.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
