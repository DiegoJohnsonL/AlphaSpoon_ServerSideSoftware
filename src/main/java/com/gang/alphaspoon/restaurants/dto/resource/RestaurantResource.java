package com.gang.alphaspoon.restaurants.dto.resource;

import com.gang.alphaspoon.model.AuditModel;

import java.util.Date;

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
