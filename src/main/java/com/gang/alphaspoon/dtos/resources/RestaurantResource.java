package com.gang.alphaspoon.dtos.resources;

import com.gang.alphaspoon.entity.AuditModel;

public class RestaurantResource extends AuditModel {

    private Long id;
    private String name;
    private Integer phoneNumber;
    private String email;


    public Long getId() {
        return id;
    }

    public RestaurantResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RestaurantResource setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public RestaurantResource setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RestaurantResource setEmail(String email) {
        this.email = email;
        return this;
    }
}
