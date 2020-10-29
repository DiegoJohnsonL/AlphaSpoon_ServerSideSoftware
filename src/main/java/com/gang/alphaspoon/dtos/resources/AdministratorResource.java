package com.gang.alphaspoon.dtos.resources;

import com.gang.alphaspoon.entity.AuditModel;

public class AdministratorResource extends AuditModel {

    private Long id;
    private String name;
    private Integer dni;
    private Integer phoneNumber;
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

    public Integer getDni() {
        return dni;
    }

    public AdministratorResource setDni(Integer dni) {
        this.dni = dni;
        return this;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public AdministratorResource setPhoneNumber(Integer phoneNumber) {
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
