package com.gang.alphaspoon.dtos.resources;

import com.gang.alphaspoon.domain.entity.AuditModel;

public class AdministratorResource extends AuditModel {
    private Long id;
    private String name;
    private int dni;
    private int phoneNumber;
    private String email;

    public Long getId() {
        return id;
    }

    public AdministratorResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AdministratorResource setName(String name) {
        this.name = name;
        return this;
    }

    public int getDni() {
        return dni;
    }

    public AdministratorResource setDni(int dni) {
        this.dni = dni;
        return this;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public AdministratorResource setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AdministratorResource setEmail(String email) {
        this.email = email;
        return this;
    }
}
