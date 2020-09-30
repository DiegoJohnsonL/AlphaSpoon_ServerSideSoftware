package com.gang.alphaspoon.users.domain.model;


import com.gang.alphaspoon.model.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "customers")
public class Customer extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @NotNull
    private int phoneNumber;
    @NotNull
    @Email
    private String email;

    public Customer(Long id, @NotNull String name, @NotNull String password, @NotNull int phoneNumber, @NotNull @Email String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Customer() {
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
