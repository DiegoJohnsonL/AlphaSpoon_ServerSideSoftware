package com.gang.alphaspoon.repository;

import com.gang.alphaspoon.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

}
