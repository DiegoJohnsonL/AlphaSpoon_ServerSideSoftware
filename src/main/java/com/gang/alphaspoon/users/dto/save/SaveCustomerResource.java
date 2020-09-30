package com.gang.alphaspoon.users.dto.save;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class SaveCustomerResource {

    @NotNull(message = "Missing customer name")
    @Length(min = 3, message = "Name needs to have between 3 to 50 characters")
    private String name;
    @NotNull(message = "Missing password")
    @Length(min = 6, max = 16, message = "Password needs to have between 3 to 50 characters")
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @NotNull
    private int phoneNumber;
    @Email(message = "Email not valid")
    private String email;


    public String getName() {
        return name;
    }

    public SaveCustomerResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SaveCustomerResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public SaveCustomerResource setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public SaveCustomerResource setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SaveCustomerResource setEmail(String email) {
        this.email = email;
        return this;
    }
}
