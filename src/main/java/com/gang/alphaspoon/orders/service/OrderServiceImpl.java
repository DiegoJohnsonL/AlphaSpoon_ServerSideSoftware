package com.gang.alphaspoon.orders.service;

import com.gang.alphaspoon.exceptions.GeneralServiceException;
import com.gang.alphaspoon.exceptions.ResourceNotFoundException;
import com.gang.alphaspoon.exceptions.ValidateServiceException;
import com.gang.alphaspoon.orders.domain.model.Order;
import com.gang.alphaspoon.orders.domain.model.OrderLine;
import com.gang.alphaspoon.orders.domain.repository.OrderLineRepository;
import com.gang.alphaspoon.orders.domain.repository.OrderRepository;
import com.gang.alphaspoon.orders.domain.service.OrderService;
import com.gang.alphaspoon.orders.validators.OrderValidator;
import com.gang.alphaspoon.restaurants.domain.model.Product;
import com.gang.alphaspoon.restaurants.domain.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderLineRepository orderLineRepository;


    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        try {
            return orderRepository.findAll(pageable);
        } catch (ValidateServiceException | ResourceNotFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override //Todo
    public Page<Order> getAllOrdersByCustomerId(Long customerId, Pageable pageable) {
        return null;
    }

    @Override
    public Order getOrderById(Long orderId) {
        try {
            return orderRepository.findById(orderId)
                    .orElseThrow(() -> new ResourceNotFoundException("La orden no existe"));
        } catch (ValidateServiceException | ResourceNotFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override //Todo
    public Order getOrderByIdAndCustomerId(Long orderId, Long customerId) {
        return null;
    }

    @Override
    @Transactional
    public Order createOrder(Order order) {
        try {
            OrderValidator.save(order);

            double total = 0;
            for(OrderLine line : order.getLines()) {
                Product product = productRepository.findById(line.getProduct().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("No existe el producto " + line.getProduct().getId()));
                line.setPrice(product.getPrice());
                line.setTotal(product.getPrice() * line.getQuantity());
                total += line.getTotal();
            }
            order.setTotal(total);
            order.getLines().forEach(line -> line.setOrder(order));

            //Create Order
            if(order.getId() == null) {
                order.setRegDate(LocalDateTime.now());
                return orderRepository.save(order);
            }
            //Update Order
            Order savedOrder = orderRepository.findById(order.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("La orden no existe"));
            //RegDate no se cambia, se mantiene la de creacion
            order.setRegDate(savedOrder.getRegDate());

            List<OrderLine> deletedLines = savedOrder.getLines();//Obtiene las lineas asociadas a la order obtenida
            deletedLines.removeAll(order.getLines());//Elimina las lineas asociadas a la orden
            orderLineRepository.deleteAll(deletedLines);

            return orderRepository.save(order);
        } catch (ValidateServiceException | ResourceNotFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> deleteOrder(Long orderId) {
        try {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new ResourceNotFoundException("La orden no existe"));

            orderRepository.delete(order);
            return ResponseEntity.ok().build();

        } catch (ValidateServiceException | ResourceNotFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
}
