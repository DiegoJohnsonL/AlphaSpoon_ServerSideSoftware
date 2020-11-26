package com.gang.alphaspoon.controllers;

import com.gang.alphaspoon.converters.OrderConverter;
import com.gang.alphaspoon.dtos.OrderDTO;
import com.gang.alphaspoon.entity.Order;
import com.gang.alphaspoon.services.OrderService;
import com.gang.alphaspoon.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderConverter converter;

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<WrapperResponse<List<OrderDTO>>> getAllOrders(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize)
    {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Order> orders = orderService.findAll(page);
        return new WrapperResponse<>(true, "success", converter.fromEntity(orders))
                .createResponse();
    }

    @GetMapping("/users/{userId}/orders/{orderId}")
    public ResponseEntity<WrapperResponse<OrderDTO>> getOrderByIdAndCustomerId(@PathVariable(name = "userId") Long userId,
                                                    @PathVariable(name="orderId") Long orderId){
        return new WrapperResponse<>(true, "success", converter.fromEntity(orderService.findById(orderId))).createResponse();
    }

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<WrapperResponse<OrderDTO>> createOrder(@PathVariable(name = "userId") Long userId, @RequestBody OrderDTO order) {
        return new WrapperResponse<>(true, "success", converter.fromEntity(orderService.save(converter.fromDTO(order)))).createResponse();
    }

    @PutMapping("/users/{userId}/orders/{orderId}")
    public ResponseEntity<WrapperResponse<OrderDTO>> updateOrder(@PathVariable(name = "userId") Long userId, @RequestBody OrderDTO order){
        return new WrapperResponse<>(true, "success", converter.fromEntity(orderService.save(converter.fromDTO(order)))).createResponse();
    }

    @DeleteMapping("/users/{userId}/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name = "userId") Long userId, @PathVariable(name="orderId") Long orderId){
        orderService.delete(orderId);
        return new WrapperResponse<>(true, "success", null).createResponse();
    }

}
