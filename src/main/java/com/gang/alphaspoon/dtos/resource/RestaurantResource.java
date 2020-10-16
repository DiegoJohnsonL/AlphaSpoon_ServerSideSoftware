package com.gang.alphaspoon.dtos.resource;

import com.gang.alphaspoon.domain.entity.AuditModel;

public class RestaurantResource extends AuditModel {
    private Long id;
    private String name;
    private int phoneNumber;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public RestaurantResource setPhoneNumber(int phoneNumber) {
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
