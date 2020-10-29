package com.gang.alphaspoon.dtos.resources;

import com.gang.alphaspoon.entity.AuditModel;
import com.gang.alphaspoon.entity.Customer;

public class AddressResource extends AuditModel {
    private Long id;
    private String tDir;
    private Customer customer;

    public AddressResource setId (long id){
        this.id = id;
        return this;
    }
    public Long getId() {
        return id;
    }

    public Customer getCustomer(){
        return customer;
    }
    public AddressResource setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public AddressResource settDir(String tDir) {
        this.tDir = tDir;
        return this;
    }

    public String gettDir(){return tDir;}
}
