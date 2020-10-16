package com.gang.alphaspoon.dtos.request;

import com.gang.alphaspoon.restaurants.dto.save.SaveProductResource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveOrderLineResource {

    @NotNull
    private SaveProductResource product;
    @NotNull
    private Double price;
    @NotNull
    @Size(min = 1, message = "Cantidad minima de 1")
    private Double quantity;
    @NotNull
    private Double total;
}
