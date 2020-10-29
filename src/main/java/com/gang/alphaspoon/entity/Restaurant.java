package com.gang.alphaspoon.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull
    private String name;
    @Email
    @Column(unique = true)
    private String email;
    @NotNull
    private Integer phoneNumber;

    public Restaurant(Long id, @NotNull String name, @Email String email, @NotNull int phoneNumber) {
        Id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Restaurant() {
    }

    public Long getId() {
        return Id;
    }

    public Restaurant setId(Long id) {
        Id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Restaurant setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Restaurant setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public Restaurant setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}