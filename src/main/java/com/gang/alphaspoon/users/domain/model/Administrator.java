package com.gang.alphaspoon.users.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gang.alphaspoon.model.AuditModel;
import com.gang.alphaspoon.restaurants.domain.model.Restaurant;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
    private int dni;
    @NotNull
    private int phoneNumber;
    @NotNull
    @Email
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)  // Un post puede tener varios comentarios - muchos a uno - Al crear no quiero un comment sin un post(optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false) // cual va a ser la columna que hara de foreing key en la tabla de comment
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore // en caso se creo un archivo JSON, no agregar la columna post
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

    public int getDni() {
        return dni;
    }

    public Administrator setDni(int dni) {
        this.dni = dni;
        return this;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public Administrator setPhoneNumber(int phoneNumber) {
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
