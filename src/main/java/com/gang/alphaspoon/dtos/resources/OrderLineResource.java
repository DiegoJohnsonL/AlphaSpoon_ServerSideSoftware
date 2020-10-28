package com.gang.alphaspoon.dtos.resources;

import com.gang.alphaspoon.domain.entity.AuditModel;

public class OrderLineResource extends AuditModel {
    private Long id;
    private ProductResource product;
    private Double price;
    private Double quantity;
    private Double total;

    public Long getId() {
        return id;
    }

    public OrderLineResource setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductResource getProduct() {
        return product;
    }

    public OrderLineResource setProduct(ProductResource product) {
        this.product = product;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public OrderLineResource setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public OrderLineResource setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public OrderLineResource setTotal(Double total) {
        this.total = total;
        return this;
    }
}
