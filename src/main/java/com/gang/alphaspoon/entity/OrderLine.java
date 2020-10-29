package com.gang.alphaspoon.entity;

import javax.persistence.*;

@Entity
@Table(name="order_lines")
public class OrderLine extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    @Column(name="price", nullable = false)
    private Double price;

    @Column(name="quantity", nullable = false)
    private Double quantity;

    @Column(name="total", nullable = false)
    private Double total;

    public OrderLine() {}
    public OrderLine(Long id, Order order, Product product, Double price, Double quantity, Double total) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public OrderLine setId(Long id) {
        this.id = id;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public OrderLine setOrder(Order order) {
        this.order = order;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public OrderLine setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public OrderLine setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public OrderLine setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public OrderLine setTotal(Double total) {
        this.total = total;
        return this;
    }
}
