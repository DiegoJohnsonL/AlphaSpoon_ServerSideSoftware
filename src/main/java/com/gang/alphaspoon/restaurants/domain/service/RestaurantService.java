package com.gang.alphaspoon.restaurants.domain.service;

import com.gang.alphaspoon.restaurants.domain.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RestaurantService {
    Page<Restaurant> getAllRestaurants(Pageable pageable);
    Restaurant getRestaurantById(Long restaurantId);
    Restaurant getRestaurantByEmail(String email);
    Restaurant create(Restaurant restaurant);
    Restaurant updateRestaurant(Long restaurantId,Restaurant restaurant );
    ResponseEntity<?> deleteRestaurant(Long restaurantId);

}
