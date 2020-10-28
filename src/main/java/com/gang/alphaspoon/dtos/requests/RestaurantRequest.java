package com.gang.alphaspoon.dtos.requests;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class RestaurantRequest {

    @NotNull(message = "Missing Restaurant name")
    @Length(min = 3, message = "Name needs to have between 3 to 50 characters")
    private String name;
    @NotNull
    private int phoneNumber;
    @Email(message = "Email not valid")
    private String email;

    public String getName() {
        return name;
    }

    public RestaurantRequest setName(String name) {
        this.name = name;
        return this;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public RestaurantRequest setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RestaurantRequest setEmail(String email) {
        this.email = email;
        return this;
    }
}
