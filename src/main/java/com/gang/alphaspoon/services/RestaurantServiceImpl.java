package com.gang.alphaspoon.services;

import com.gang.alphaspoon.exceptions.ResourceNotFoundException;
import com.gang.alphaspoon.domain.entity.Restaurant;
import com.gang.alphaspoon.domain.repository.RestaurantRepository;
import com.gang.alphaspoon.domain.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private  RestaurantRepository restaurantRepository;

    @Override
    public Page<Restaurant> getAllRestaurants(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    @Override
    public Restaurant getRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(()->new ResourceNotFoundException("Restaurant with"+ restaurantId + "not found"  ));
    }
    @Override
    public Restaurant getRestaurantByEmail(String email) {
        return restaurantRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("Restaurant with" + email + "not found" ));
    }
    @Override
    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, Restaurant restaurantRequest) {
        return restaurantRepository.findById(restaurantId).map(restaurant -> {
            restaurant.setName(restaurantRequest.getName());
            restaurant.setEmail(restaurantRequest.getEmail());
            restaurant.setPhoneNumber(restaurantRequest.getPhoneNumber());
            return restaurantRepository.save(restaurant);
        }).orElseThrow(()->new ResourceNotFoundException("Restaurant with" + restaurantId + "not found"));
    }
    @Override
    public ResponseEntity<?> deleteRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).map(restaurant -> {
            restaurantRepository.delete(restaurant);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Restaurant with" + restaurantId + "not found"));
    }
}
