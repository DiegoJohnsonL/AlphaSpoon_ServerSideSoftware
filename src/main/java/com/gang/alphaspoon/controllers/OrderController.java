package com.gang.alphaspoon.controllers;

import com.gang.alphaspoon.domain.entity.Order;
import com.gang.alphaspoon.domain.service.OrderService;
import com.gang.alphaspoon.dtos.resource.OrderResource;
import com.gang.alphaspoon.dtos.request.OrderRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public Page<OrderResource> getAllOrders(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize, Pageable pageable){
        List<OrderResource> resourceList = orderService.getAllOrders(pageable).getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resourceList, pageable, resourceList.size()) ;
    }

    @GetMapping("{orderId}")
    public OrderResource getOrderById(@PathVariable(name="orderId") Long orderId){
        return convertToResource(orderService.getOrderById(orderId));
    }

    @PostMapping
    public OrderResource createOrder(@Valid @RequestBody OrderRequest resource) {
        return convertToResource(orderService.createOrder(convertToEntity(resource)));
    }

    @PutMapping
    public OrderResource updateOrder(@Valid @RequestBody OrderRequest order){
        return convertToResource(orderService.createOrder(convertToEntity(order)));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name="orderId") Long orderId){
        return orderService.deleteOrder(orderId);
    }

    private Order convertToEntity(OrderRequest resource){return mapper.map(resource,Order.class);}
    private OrderResource convertToResource(Order entity){return mapper.map(entity, OrderResource.class);}

}
