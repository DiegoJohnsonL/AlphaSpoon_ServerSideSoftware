package com.gang.alphaspoon.services.Impl;

import com.gang.alphaspoon.entity.Customer;
import com.gang.alphaspoon.exceptions.GeneralServiceException;
import com.gang.alphaspoon.exceptions.NoDataFoundException;
import com.gang.alphaspoon.exceptions.ValidateServiceException;
import com.gang.alphaspoon.entity.Order;
import com.gang.alphaspoon.entity.OrderLine;
import com.gang.alphaspoon.repository.CustomerRepository;
import com.gang.alphaspoon.repository.OrderLineRepository;
import com.gang.alphaspoon.repository.OrderRepository;
import com.gang.alphaspoon.services.OrderService;
import com.gang.alphaspoon.validators.OrderValidator;
import com.gang.alphaspoon.entity.Product;
import com.gang.alphaspoon.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Page<Order> getAllOrdersByCustomerId(Long customerId, Pageable pageable) {
        try {
            return orderRepository.findAllByCustomerId(customerId, pageable);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Order getOrderByIdAndCustomerId(Long customerId, Long orderId) {
        try {
            return orderRepository.findByIdAndCustomerId(orderId, customerId)
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Order createOrder(Long customerId, Order order) {
        try {
            OrderValidator.save(order);

            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NoDataFoundException("EL usuario no existe"));

            double total = 0;
            for(OrderLine line : order.getLines()) {
                Product product = productRepository.findById(line.getProduct().getId())
                        .orElseThrow(() -> new NoDataFoundException("No existe el producto " + line.getProduct().getId()));
                line.setPrice(product.getPrice());
                line.setTotal(product.getPrice() * line.getQuantity());
                total += line.getTotal();
            }
            order.setTotal(total);
            order.getLines().forEach(line -> line.setOrder(order));

            //Create Order
            if(order.getId() == null) {
                order.setRegDate(LocalDateTime.now());
                //order.setCustomer(customer);
                return orderRepository.save(order);
            }
            //Update Order
            Order savedOrder = orderRepository.findById(order.getId())
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));
            //RegDate no se cambia, se mantiene la de creacion
            order.setRegDate(savedOrder.getRegDate());

            List<OrderLine> deletedLines = savedOrder.getLines();//Obtiene las lineas asociadas a la order obtenida
            deletedLines.removeAll(order.getLines());//Elimina las lineas asociadas a la orden
            orderLineRepository.deleteAll(deletedLines);

            return orderRepository.save(order);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    public ResponseEntity<?> deleteOrder(Long customerId, Long orderId) {
        try {
            return orderRepository.findByIdAndCustomerId(orderId, customerId).map(order -> {
                orderRepository.delete(order);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new NoDataFoundException("Order not found with Id " + orderId + " and CustomerId " + customerId));

        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
}
