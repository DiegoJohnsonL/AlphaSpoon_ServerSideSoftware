package com.gang.alphaspoon.restaurants.controller;

import com.gang.alphaspoon.restaurants.domain.model.Restaurant;
import com.gang.alphaspoon.restaurants.domain.service.RestaurantService;
import com.gang.alphaspoon.restaurants.dto.resource.RestaurantResource;
import com.gang.alphaspoon.restaurants.dto.save.SaveRestaurantResource;
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
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private ModelMapper mapper;


    @GetMapping
    public Page<RestaurantResource> getAllRestaurants(Pageable pageable){
        //Todo: Test this implementation of get all
        //return restaurantService.getAllRestaurants(pageable).map(this::convertToResource);
        //If it doesn't work use this
        List<RestaurantResource> resources = restaurantService.getAllRestaurants(pageable).getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @GetMapping("/{restaurantId}")
    public RestaurantResource getRestaurantById(@PathVariable(name = "restaurantId") Long restaurantId){
        return convertToResource(restaurantService.getRestaurantById(restaurantId));
    }
    @GetMapping("/{restaurantEmail:.+}")
    public RestaurantResource getRestaurantByEmail(@PathVariable(name = "restaurantEmail") String restaurant){
        return convertToResource(restaurantService.getRestaurantByEmail(restaurant));
    }

    @PostMapping
    public RestaurantResource createRestaurant(
            @Valid @RequestBody SaveRestaurantResource restaurantResource){
        return convertToResource(restaurantService
                .create(convertToEntity(restaurantResource)));
    }

    @PutMapping("/{restaurantId}")
    public  RestaurantResource updateRestaurant(
            @PathVariable(name = "restaurantId") Long restaurantId,
            @Valid @RequestBody SaveRestaurantResource restaurantResource){
        return convertToResource(restaurantService.updateRestaurant(restaurantId, convertToEntity(restaurantResource)));
    }

    @DeleteMapping("{restaurantId}")
    public ResponseEntity<?> deleteRestaurant(
            @PathVariable(name = "restaurantId") Long restaurantId){
        return restaurantService.deleteRestaurant(restaurantId);
    }

    private Restaurant convertToEntity(@Valid SaveRestaurantResource resource){return mapper.map(resource,Restaurant.class);}
    private RestaurantResource convertToResource(Restaurant entity){return mapper.map(entity, RestaurantResource.class);}

}
