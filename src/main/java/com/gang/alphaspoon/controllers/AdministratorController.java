package com.gang.alphaspoon.controllers;
import com.gang.alphaspoon.dtos.resources.RestaurantResource;
import com.gang.alphaspoon.entity.Administrator;
import com.gang.alphaspoon.services.AdministratorService;
import com.gang.alphaspoon.dtos.requests.AdministratorRequest;
import com.gang.alphaspoon.dtos.resources.AdministratorResource;
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
@RequestMapping
public class AdministratorController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AdministratorService administratorService;

    @GetMapping("/restaurants/{restaurantId}/administrators")
    public ResponseEntity<WrapperResponse<Page<AdministratorResource>>> getAllAdministratorsByRestaurantId(@PathVariable(name = "restaurantId") Long restaurantId, Pageable pageable){
        List<AdministratorResource> resources = administratorService.getAllAdministratorsByRestaurantId(restaurantId, pageable).getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        Page<AdministratorResource> page = new PageImpl<>(resources, pageable, resources.size());
        return new WrapperResponse<>(true, "success", page).createResponse();
    }

    @GetMapping("/restaurants/{restaurantId}/administrators/{adminId}")
    public ResponseEntity<WrapperResponse<AdministratorResource>> getAdministratorByIdAndRestaurantId(@PathVariable(name = "restaurantId") Long restaurantId,
                                                                     @PathVariable(name = "adminId") Long adminId){
        return new WrapperResponse<>(true, "success",
                convertToResource(administratorService.getAdministratorByIdAndRestaurantId(adminId, restaurantId))).createResponse();
    }

    @GetMapping("/administrators/email/{adminEmail:.+}")
    public ResponseEntity<WrapperResponse<AdministratorResource>> getAdministratorByEmail(@PathVariable(name = "adminEmail") String adminEmail){
        return new WrapperResponse<>(true, "success",
                convertToResource(administratorService.getAdministratorByEmail(adminEmail))).createResponse();
    }

    @GetMapping("/administrators/dni/{dni}")
    public ResponseEntity<WrapperResponse<AdministratorResource>> getAdministratorByDni(@PathVariable(name = "dni") Integer dni){
        return new WrapperResponse<>(true, "success",
                convertToResource(administratorService.getAdministratorByDni(dni))).createResponse();

    }

    @PostMapping("/restaurants/{restaurantId}/administrators")
    public ResponseEntity<WrapperResponse<AdministratorResource>> createAdministrator(@PathVariable(name = "restaurantId") Long restaurantId,
                                                                                      @Valid @RequestBody AdministratorRequest administratorResource){
        return new WrapperResponse<>(true, "success",
                convertToResource(administratorService.createAdministrator(restaurantId, convertToEntity(administratorResource)))).createResponse();
    }

    @PutMapping("/restaurants/{restaurantId}/administrators/{adminId}")
    public ResponseEntity<WrapperResponse<AdministratorResource>> updateAdministrator(@PathVariable(name = "restaurantId") Long restaurantId,
                                                                                      @PathVariable(name = "adminId") Long adminId, AdministratorRequest administratorResource){
        return new WrapperResponse<>(true, "success",
                convertToResource(administratorService.updateAdministrator(restaurantId, adminId, convertToEntity(administratorResource)))).createResponse();

    }

    @DeleteMapping("/restaurants/{restaurantId}/administrators/{adminId}")
    public ResponseEntity<?> deleteAdministrator(@PathVariable(name = "restaurantId") Long restaurantId, @PathVariable(name = "adminId") Long adminId){

        return new WrapperResponse<>(true, "success",
                administratorService.deleteAdministrator(restaurantId, adminId)).createResponse();
    }

    private Administrator convertToEntity(AdministratorRequest resource){return mapper.map(resource,Administrator.class);}
    private AdministratorResource convertToResource(Administrator entity){return mapper.map(entity, AdministratorResource.class);}
}
