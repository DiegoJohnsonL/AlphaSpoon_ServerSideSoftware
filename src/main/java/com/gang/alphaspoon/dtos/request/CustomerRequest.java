package com.gang.alphaspoon.dtos.request;

import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CustomerRequest {
    @NotNull(message = "Missing customer name")
    @Length(min = 3, message = "Name needs to have between 3 to 50 characters")
    private String name;
    @NotNull(message = "Missing password")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @NotNull
    private int phoneNumber;
    @Email(message = "Email not valid")
    private String email;


    public String getName() {
        return name;
    }

    public CustomerRequest setName(String name) {
        this.name = name;
        return this;
    }



    public Date getBirthday() {
        return birthday;
    }

    public CustomerRequest setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public CustomerRequest setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerRequest setEmail(String email) {
        this.email = email;
        return this;
    }
}
