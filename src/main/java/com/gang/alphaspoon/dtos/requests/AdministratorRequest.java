package com.gang.alphaspoon.dtos.requests;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class AdministratorRequest {
    @NotNull(message = "Missing customer name")
    @Length(min = 3, message = "Name needs to have between 3 to 50 characters")
    private String name;

    @NotNull(message = "Missing password")
    @Length(min = 6, max = 16, message = "Password needs to have between 3 to 50 characters")
    private String password;

    @NotNull(message = "Missing password")
    @Length(min = 8, max = 8, message = "DNI needs to have 8 digits")
    private int dni;

    @NotNull
    private int phoneNumber;

    @Email(message = "Email not valid")
    private String email;


    public String getName() {
        return name;
    }

    public AdministratorRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AdministratorRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getDni() {
        return dni;
    }

    public AdministratorRequest setDni(int dni) {
        this.dni = dni;
        return this;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public AdministratorRequest setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AdministratorRequest setEmail(String email) {
        this.email = email;
        return this;
    }
}
