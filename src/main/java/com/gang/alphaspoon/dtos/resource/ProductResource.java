package com.gang.alphaspoon.dtos.resource;


import com.gang.alphaspoon.domain.entity.AuditModel;

public class ProductResource extends AuditModel {

    private Long id;
    private String name;
    private Double price;

    public Long getId() {
        return id;
    }

    public ProductResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductResource setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ProductResource setPrice(Double price) {
        this.price = price;
        return this;
    }
}
