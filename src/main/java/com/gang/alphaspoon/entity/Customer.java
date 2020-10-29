package com.gang.alphaspoon.entity;


import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Builder
@Table(name = "customers")
public class Customer extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @Size (max = 150)
    private String password;
    @NotNull
    @Temporal(TemporalType.DATE) //Todo: Change to LocalDateTime Type of variable
    private Date birthday;
    @NotNull
    private int phoneNumber;
    @NotNull
    @Email
    private String email;

    public Customer() {
    }
    public Customer(Long id, @NotNull String name, @NotNull String password, @NotNull Date birthday, @NotNull int phoneNumber, @NotNull @Email String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public Customer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Customer setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Customer setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public Customer setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }
}
