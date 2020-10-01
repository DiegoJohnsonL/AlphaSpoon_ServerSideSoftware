package com.gang.alphaspoon.orders.validators;

import com.gang.alphaspoon.exceptions.ValidateServiceException;
import com.gang.alphaspoon.orders.domain.model.Order;
import com.gang.alphaspoon.orders.domain.model.OrderLine;

public class OrderValidator {
    public static void save(Order order) {

        if(order.getLines() == null || order.getLines().isEmpty()) {
            throw new ValidateServiceException("Las líneas son requeridas");
        }

        for(OrderLine line: order.getLines()) {
            if(line.getProduct() == null || line.getProduct().getId() == null) {
                throw new ValidateServiceException("El producto es requerido");
            }
            if(line.getPrice() == null){
                throw new ValidateServiceException("El precio es requerido");
            }
            if(line.getPrice() < 0) {
                throw new ValidateServiceException("El precio es incorrecto");
            }

            if(line.getQuantity() == null){
                throw new ValidateServiceException("La cantidad es requerido");
            }
            if(line.getQuantity() < 0) {
                throw new ValidateServiceException("La cantidad es incorrecto");
            }
        }
    }
}
