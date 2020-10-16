package com.gang.alphaspoon.domain.service;

import com.gang.alphaspoon.domain.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    //Todo: Add methods to search with customer id
    Page<Order> getAllOrders(Pageable pageable);
    Page<Order> getAllOrdersByCustomerId(Long customerId, Pageable pageable);
    Order getOrderById(Long orderId);
    Order getOrderByIdAndCustomerId(Long orderId, Long customerId);
    Order createOrder(Order order);
    ResponseEntity<?> deleteOrder(Long orderId);

}
