package com.gang.alphaspoon.validators;

import com.gang.alphaspoon.entity.Restaurant;
import com.gang.alphaspoon.exceptions.ValidateServiceException;

public class RestaurantValidator {
    public static void validate(Restaurant restaurant){
        if(restaurant.getName()==null || restaurant.getName().trim().isEmpty()){
            throw new ValidateServiceException("El nombre de usuario es requerido");
        }

        if(restaurant.getName().length()>30){
            throw new ValidateServiceException("El nombre de usuario es muy largo (max 30)");
        }
    }
}
