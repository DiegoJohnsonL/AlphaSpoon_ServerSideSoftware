package com.gang.alphaspoon.validators;

import com.gang.alphaspoon.entity.Customer;
import com.gang.alphaspoon.exceptions.ValidateServiceException;

public class CustomerValidator {
    public static void validate(Customer customer){
        if(customer.getEmail()==null || customer.getEmail().trim().isEmpty()){
            throw new ValidateServiceException("Es obligatorio ingresar un Correo");
        }
        if(customer.getPassword()==null || customer.getPassword().trim().isEmpty()){
            throw new ValidateServiceException("Es obligatorio ingresar una Contraseña");

        }
        if(customer.getPassword().length() > 16){
            throw new ValidateServiceException("La Contraseña debe ser menor de 16 caracteres");
        }
    }
}
