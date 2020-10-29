package com.gang.alphaspoon.dtos.resources;

import com.gang.alphaspoon.entity.AuditModel;
import lombok.Builder;

import java.util.Date;

@Builder
public class CustomerResource extends AuditModel {
       private Long id;
       private String name;
       private Date birthday;
       private int phoneNumber;
       private String email;

    public CustomerResource(Long id, String name, Date birthday, int phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public CustomerResource() {

    }


    public Long getId() {
        return id;
    }

    public CustomerResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CustomerResource setName(String name) {
        this.name = name;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public CustomerResource setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public CustomerResource setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerResource setEmail(String email) {
        this.email = email;
        return this;
    }
}
