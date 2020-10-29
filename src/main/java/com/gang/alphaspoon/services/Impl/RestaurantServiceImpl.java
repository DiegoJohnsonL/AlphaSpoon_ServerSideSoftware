package com.gang.alphaspoon.services.Impl;

import com.gang.alphaspoon.entity.Restaurant;
import com.gang.alphaspoon.exceptions.GeneralServiceException;
import com.gang.alphaspoon.exceptions.NoDataFoundException;
import com.gang.alphaspoon.exceptions.ValidateServiceException;
import com.gang.alphaspoon.repository.RestaurantRepository;
import com.gang.alphaspoon.services.RestaurantService;
import com.gang.alphaspoon.validators.RestaurantValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Page<Restaurant> getAllRestaurants(Pageable pageable) {
        try{
            return restaurantRepository.findAll(pageable);

        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Restaurant getRestaurantById(Long restaurantId) {
        try{
            return restaurantRepository.findById(restaurantId)
                    .orElseThrow(()->new NoDataFoundException("Restaurant with"+ restaurantId + "not found"  ));

        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Restaurant getRestaurantByEmail(String email) {
        try{
            return restaurantRepository.findByEmail(email)
                    .orElseThrow(()->new NoDataFoundException("Restaurant with" + email + "not found" ));

        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }
    @Transactional
    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        try{
            RestaurantValidator.validate(restaurant);
            return restaurantRepository.save(restaurant);

        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }
    @Transactional
    @Override
    public Restaurant updateRestaurant(Long restaurantId, Restaurant restaurantRequest) {
        try{
            return restaurantRepository.findById(restaurantId).map(restaurant -> {
                restaurant.setName(restaurantRequest.getName());
                restaurant.setEmail(restaurantRequest.getEmail());
                restaurant.setPhoneNumber(restaurantRequest.getPhoneNumber());
                return restaurantRepository.save(restaurant);
            }).orElseThrow(()->new NoDataFoundException("Restaurant with" + restaurantId + "not found"));

        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }
    @Transactional
    @Override
    public ResponseEntity<?> deleteRestaurant(Long restaurantId) {
        try{
            return restaurantRepository.findById(restaurantId).map(restaurant -> {
                restaurantRepository.delete(restaurant);
                return ResponseEntity.ok().build();
            }).orElseThrow(()->new NoDataFoundException("Restaurant with" + restaurantId + "not found"));

        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }
}
