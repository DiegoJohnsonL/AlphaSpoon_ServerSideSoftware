package com.gang.alphaspoon.services;

import com.gang.alphaspoon.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    Page<Order> getAllOrdersByCustomerId(Long customerId, Pageable pageable);
    Order getOrderByIdAndCustomerId(Long orderId, Long customerId);
    Order createOrder(Long customerId, Order order);
    ResponseEntity<?> deleteOrder(Long customerId, Long orderId);

}
