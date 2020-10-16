package com.gang.alphaspoon.dtos.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class SaveProductResource {

    @NotNull(message = "Missing Product name")
    @Length(min = 3, max = 100, message = "Name needs to have between 3 to 50 characters")
    private String name;
    @NotNull(message = "El precio es requerido")
    private Double price;

    public String getName() {
        return name;
    }

    public SaveProductResource setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public SaveProductResource setPrice(Double price) {
        this.price = price;
        return this;
    }
}
