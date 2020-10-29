package com.gang.alphaspoon.services;

import com.gang.alphaspoon.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RestaurantService {
    Page<Restaurant> getAllRestaurants(Pageable pageable);
    Restaurant getRestaurantById(Long restaurantId);
    Restaurant getRestaurantByEmail(String email);
    Restaurant createRestaurant(Restaurant restaurant);
    Restaurant updateRestaurant(Long restaurantId,Restaurant restaurant );
    ResponseEntity<?> deleteRestaurant(Long restaurantId);

}
