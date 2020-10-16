package com.gang.alphaspoon.dtos.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class SaveRestaurantResource {

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

    public SaveRestaurantResource setName(String name) {
        this.name = name;
        return this;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public SaveRestaurantResource setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SaveRestaurantResource setEmail(String email) {
        this.email = email;
        return this;
    }
}
