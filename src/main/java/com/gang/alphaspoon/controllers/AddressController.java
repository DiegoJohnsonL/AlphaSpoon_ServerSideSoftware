package com.gang.alphaspoon.controllers;

import com.gang.alphaspoon.dtos.resources.AddressResource;
import com.gang.alphaspoon.dtos.resources.AdministratorResource;
import com.gang.alphaspoon.services.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public Page<AddressResource> getAllAddress(Pageable pageable){
        return null;
        /*List<AdministratorResource> resources = addressService.getAllAddress(pageable).getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());*/
    }
}