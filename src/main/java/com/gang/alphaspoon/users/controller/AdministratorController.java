package com.gang.alphaspoon.users.controller;
import com.gang.alphaspoon.users.domain.model.Administrator;
import com.gang.alphaspoon.users.domain.service.AdministratorService;
import com.gang.alphaspoon.users.dto.resource.AdministratorResource;
import com.gang.alphaspoon.users.dto.save.SaveAdministratorResource;
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
@RequestMapping("/administrators")
public class AdministratorController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AdministratorService administratorService;

    @GetMapping
    public Page<AdministratorResource> getAllAdministrators(Pageable pageable){
        List<AdministratorResource> resources = administratorService.getAllAdministrators(pageable).getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }
    @GetMapping("/{adminId}")
    public AdministratorResource getAdministratorById(@PathVariable(name = "adminId") Long adminId){
        return convertToResource(administratorService.getAdministratorById(adminId));
    }

    @GetMapping("/{adminEmail:.+}")
    public AdministratorResource getAdministratorByEmail(@PathVariable(name = "adminEmail") String adminEmail){
        return convertToResource(administratorService.getAdministratorByEmail(adminEmail));
    }
    @GetMapping("/{dni}")
    public AdministratorResource getAdministratorByDni(@PathVariable(name = "dni")int dni){
        return convertToResource(administratorService.getAdministratorByDni(dni)); }

    @PostMapping
    public AdministratorResource createAdministrator(@Valid @RequestBody SaveAdministratorResource administratorResource){
        return convertToResource(administratorService.create(convertToEntity(administratorResource)));
    }

    @PutMapping("/{adminId}")
    public AdministratorResource updateAdministrator(@PathVariable(name = "adminId") Long adminId,
                                           SaveAdministratorResource administratorResource){
        return convertToResource(administratorService.updateAdministrator(adminId, convertToEntity(administratorResource)));
    }

    @DeleteMapping("/adminId}")
    public ResponseEntity<?> deleteAdministrator(@PathVariable(name = "adminId") Long adminId){
        return administratorService.deleteAdministrator(adminId);
    }

    private Administrator convertToEntity(SaveAdministratorResource resource){return mapper.map(resource,Administrator.class);}
    private AdministratorResource convertToResource(Administrator entity){return mapper.map(entity, AdministratorResource.class);}
}
