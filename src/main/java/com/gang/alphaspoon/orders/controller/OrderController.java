package com.gang.alphaspoon.orders.controller;

import com.gang.alphaspoon.orders.domain.model.Order;
import com.gang.alphaspoon.orders.domain.service.OrderService;
import com.gang.alphaspoon.orders.dto.resource.OrderResource;
import com.gang.alphaspoon.orders.dto.save.SaveOrderResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ModelMapper mapper;






    private Order convertToEntity(SaveOrderResource resource){return mapper.map(resource,Order.class);}
    private OrderResource convertToResource(Order entity){return mapper.map(entity, OrderResource.class);}

}
