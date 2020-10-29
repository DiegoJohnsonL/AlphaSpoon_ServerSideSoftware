package com.gang.alphaspoon.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderLine> lines;

    @Column(name="total", nullable = false)
    private Double total;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false) // cual va a ser la columna que hara de foreing key en la tabla de comment
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore // en caso se creo un archivo JSON, no agregar la columna post
    private Customer customer;

    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public Order setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
        return this;
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public Order setLines(List<OrderLine> lines) {
        this.lines = lines;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public Order setTotal(Double total) {
        this.total = total;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Order setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }
}
