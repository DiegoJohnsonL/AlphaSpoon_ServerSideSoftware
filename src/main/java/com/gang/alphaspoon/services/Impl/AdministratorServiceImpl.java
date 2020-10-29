package com.gang.alphaspoon.services.Impl;

import com.gang.alphaspoon.entity.Administrator;
import com.gang.alphaspoon.exceptions.GeneralServiceException;
import com.gang.alphaspoon.exceptions.NoDataFoundException;
import com.gang.alphaspoon.exceptions.ValidateServiceException;
import com.gang.alphaspoon.repository.AdministratorRepository;
import com.gang.alphaspoon.repository.RestaurantRepository;
import com.gang.alphaspoon.services.AdministratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class AdministratorServiceImpl implements AdministratorService{

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Page<Administrator> getAllAdministratorsByRestaurantId(Long restaurantId, Pageable pageable) {
        try{
            return administratorRepository.findByRestaurantId(restaurantId, pageable);
        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }
    @Override
    public Administrator getAdministratorByIdAndRestaurantId(Long administratorId, Long restaurantId) {
        try{
            return administratorRepository.findByIdAndRestaurantId(administratorId, restaurantId)
                    .orElseThrow(()->new NoDataFoundException(
                            "User with"+administratorId + "not found"));

        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Administrator getAdministratorByEmail(String email) {
        try{
            return administratorRepository.findByEmail(email).orElseThrow(()->new NoDataFoundException("User with"+
                    email + "not found"));
        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }

    @Override
    public Administrator getAdministratorByDni(Integer dni) {
        try{
            return administratorRepository.findByDni(dni).orElseThrow(()->new NoDataFoundException("User with"+
                    dni + "not found"));
        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }

    @Override
    public Administrator createAdministrator(Long restaurantId, Administrator administrator) {
        try{
            return restaurantRepository.findById(restaurantId).map(restaurant -> {
                administrator.setRestaurant(restaurant);
                return administratorRepository.save(administrator);
            }).orElseThrow(() -> new NoDataFoundException( "Restaurant", "Id", restaurantId));

        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }


    @Override
    public Administrator updateAdministrator(Long restaurantId, Long administratorId, Administrator administratorRequest) {
        try{
            if(!restaurantRepository.existsById(restaurantId))
                throw new NoDataFoundException("Restaurant", "Id", restaurantId);

            return administratorRepository.findById(administratorId).map(administrator -> {
                administrator.setName(administratorRequest.getName());
                administrator.setPassword(administratorRequest.getPassword());
                administrator.setDni(administratorRequest.getDni());
                administrator.setEmail(administratorRequest.getEmail());
                administrator.setPhoneNumber(administratorRequest.getPhoneNumber());
                return administratorRepository.save(administrator);
            }).orElseThrow(()->new NoDataFoundException("Customer", "Id", administratorId));

        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }

    @Override
    public ResponseEntity<?> deleteAdministrator(Long restaurantId, Long administratorId) {
        try{
            return administratorRepository.findByIdAndRestaurantId(administratorId, restaurantId).map(administrator -> {
                administratorRepository.delete(administrator);
                return ResponseEntity.ok().build();
            }).orElseThrow(()->new NoDataFoundException("Administrator", "Id", administratorId));

        }catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }
}
