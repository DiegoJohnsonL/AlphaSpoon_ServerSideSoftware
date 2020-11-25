package com.gang.alphaspoon.dtos.resources;

import com.gang.alphaspoon.entity.AuditModel;
import com.gang.alphaspoon.entity.User;

public class AddressResource extends AuditModel {
    private Long id;
    private String tDir;
    private User customer;

    public AddressResource setId (long id){
        this.id = id;
        return this;
    }
    public Long getId() {
        return id;
    }

    public User getCustomer(){
        return customer;
    }
    public AddressResource setCustomer(User customer) {
        this.customer = customer;
        return this;
    }

    public AddressResource settDir(String tDir) {
        this.tDir = tDir;
        return this;
    }

    public String gettDir(){return tDir;}
}
