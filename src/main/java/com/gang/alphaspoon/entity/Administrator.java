package com.gang.alphaspoon.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "administrators")
public class Administrator extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    @Column(unique = true)
    private Integer dni;
    @NotNull
    private Integer phoneNumber;
    @Email
    @Column(unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Restaurant restaurant;

    public Administrator() {
    }

    public Administrator(Long id, @NotNull String name, @NotNull String password, @NotNull int dni, @NotNull int phoneNumber, @NotNull @Email String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.dni = dni;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public Administrator setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Administrator setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Administrator setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getDni() {
        return dni;
    }

    public Administrator setDni(Integer dni) {
        this.dni = dni;
        return this;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public Administrator setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Administrator setEmail(String email) {
        this.email = email;
        return this;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Administrator setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return this;
    }
}
