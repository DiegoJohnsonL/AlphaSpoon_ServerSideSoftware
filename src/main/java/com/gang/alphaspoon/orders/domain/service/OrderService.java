package com.gang.alphaspoon.orders.domain.service;

import com.gang.alphaspoon.orders.domain.model.Order;
import org.springframework.data.domain.Page;

public interface OrderService {
    //Todo: Add fix method after creating Customer Entity
    Page<Order> getAllOrdersByCustomerId();
    Order getOrderByIdAndCustomerId();

}
