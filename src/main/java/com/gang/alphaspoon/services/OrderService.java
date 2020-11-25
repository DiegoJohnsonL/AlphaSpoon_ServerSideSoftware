package com.gang.alphaspoon.services;

import com.gang.alphaspoon.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    List<Order> findAll(Pageable pageable);
    Order findById(Long orderId);
    Order save(Order order);
    void delete(Long orderId);

}
