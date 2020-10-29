package com.gang.alphaspoon.controllers;

import com.gang.alphaspoon.entity.Restaurant;
import com.gang.alphaspoon.services.RestaurantService;
import com.gang.alphaspoon.dtos.requests.RestaurantRequest;
import com.gang.alphaspoon.dtos.resources.RestaurantResource;
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
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ModelMapper mapper;


    @GetMapping
    public ResponseEntity<WrapperResponse<Page<RestaurantResource>>> getAllRestaurants(Pageable pageable){
        List<RestaurantResource> resources = restaurantService.getAllRestaurants(pageable).getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        Page<RestaurantResource> page = new PageImpl<>(resources, pageable, resources.size());

        return new WrapperResponse<>(true, "success", page).createResponse();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<WrapperResponse<RestaurantResource>> getRestaurantById(@PathVariable(name = "restaurantId") Long restaurantId){
        return new WrapperResponse<>(true, "success",
                convertToResource(restaurantService.getRestaurantById(restaurantId))).createResponse();
    }

    @GetMapping("/email/{restaurantEmail:.+}")
    public  ResponseEntity<WrapperResponse<RestaurantResource>> getRestaurantByEmail(@PathVariable(name = "restaurantEmail") String restaurant){
        return new WrapperResponse<>(true, "success",
                convertToResource(restaurantService.getRestaurantByEmail(restaurant))).createResponse();
    }

    @PostMapping
    public ResponseEntity<WrapperResponse<RestaurantResource>> createRestaurant(
            @Valid @RequestBody RestaurantRequest restaurantResource){
        return new WrapperResponse<>(true, "success",
                convertToResource(restaurantService.createRestaurant(convertToEntity(restaurantResource)))).createResponse();
    }

    @PutMapping("/{restaurantId}")
    public  ResponseEntity<WrapperResponse<RestaurantResource>> updateRestaurant(
            @PathVariable(name = "restaurantId") Long restaurantId,
            @Valid @RequestBody RestaurantRequest restaurantResource){
        return new WrapperResponse<>(true, "success",
                convertToResource(restaurantService.updateRestaurant(restaurantId, convertToEntity(restaurantResource)))).createResponse();

    }

    @DeleteMapping("{restaurantId}")
    public ResponseEntity<?> deleteRestaurant(
            @PathVariable(name = "restaurantId") Long restaurantId){
        restaurantService.deleteRestaurant(restaurantId);
        return new WrapperResponse<>(true, "success", null).createResponse();
    }

    private Restaurant convertToEntity(@Valid RestaurantRequest resource){return mapper.map(resource,Restaurant.class);}
    private RestaurantResource convertToResource(Restaurant entity){return mapper.map(entity, RestaurantResource.class);}
}
