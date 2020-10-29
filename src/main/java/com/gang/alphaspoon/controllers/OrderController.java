package com.gang.alphaspoon.controllers;

import com.gang.alphaspoon.converters.OrderConverter;
import com.gang.alphaspoon.dtos.OrderDTO;
import com.gang.alphaspoon.entity.Order;
import com.gang.alphaspoon.services.OrderService;
import com.gang.alphaspoon.utils.WrapperResponse;
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
@RequestMapping
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderConverter converter;

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<WrapperResponse<Page<OrderDTO>>> getAllOrdersByCustomerId(
            @PathVariable(name = "customerId") Long customerId, Pageable pageable){
        List<OrderDTO> resourceList = orderService.getAllOrdersByCustomerId(customerId, pageable).getContent()
                .stream().map(converter::fromEntity).collect(Collectors.toList());
        Page<OrderDTO> page = new PageImpl<>(resourceList, pageable, resourceList.size());
        return new WrapperResponse<>(true, "success", page).createResponse();
    }

    @GetMapping("/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<WrapperResponse<OrderDTO>> getOrderByIdAndCustomerId(@PathVariable(name = "customerId") Long customerId,
                                                    @PathVariable(name="orderId") Long orderId){
        return new WrapperResponse<>(true, "success", converter.fromEntity(orderService.getOrderByIdAndCustomerId(orderId, customerId))).createResponse();
    }

    @PostMapping("/customers/{customerId}/orders")
    public ResponseEntity<WrapperResponse<OrderDTO>> createOrder(@PathVariable(name = "customerId") Long customerId, @RequestBody OrderDTO order) {
        return new WrapperResponse<>(true, "success", converter.fromEntity(orderService.createOrder(customerId, converter.fromDTO(order)))).createResponse();
    }

    @PutMapping("/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<WrapperResponse<OrderDTO>> updateOrder(@PathVariable(name = "customerId") Long customerId, @RequestBody OrderDTO order){
        return new WrapperResponse<>(true, "success", converter.fromEntity(orderService.createOrder(customerId, converter.fromDTO(order)))).createResponse();
    }

    @DeleteMapping("/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name = "customerId") Long customerId, @PathVariable(name="orderId") Long orderId){
        return new WrapperResponse<>(true, "success", orderService.deleteOrder(customerId, orderId)).createResponse();
    }

}
