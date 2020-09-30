package com.gang.alphaspoon.orders.service;

import com.gang.alphaspoon.orders.domain.model.Order;
import com.gang.alphaspoon.orders.domain.repository.OrderRepository;
import com.gang.alphaspoon.orders.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Page<Order> getAllOrdersByCustomerId() {
        return null;
    }

    @Override
    public Order getOrderByIdAndCustomerId() {
        return null;
    }
}
