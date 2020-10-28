package com.gang.alphaspoon.dtos.requests;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrderLineRequest {

    @NotNull
    private ProductRequest product;
    @NotNull
    private Double price;
    @NotNull
    @Size(min = 1, message = "Cantidad minima de 1")
    private Double quantity;
    @NotNull
    private Double total;
}
