package com.gang.alphaspoon.orders.domain.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long totalPrice;
}
