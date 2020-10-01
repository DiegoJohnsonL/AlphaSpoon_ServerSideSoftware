package com.gang.alphaspoon.orders.domain.repository;

import com.gang.alphaspoon.orders.domain.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

}
