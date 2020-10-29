package com.gang.alphaspoon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "address")
public class Address extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String tDir;


    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Restaurant customer;


    public Address() {
    }

    public Address(Long id, @NotNull String tDir, Restaurant customer) {
        this.id = id;
        this.tDir = tDir;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public Address setId(Long id) {
        this.id = id;
        return this;
    }

    public String gettDir() {
        return tDir;
    }

    public Address settDir(String tDir) {
        this.tDir = tDir;
        return this;
    }

    public Restaurant getCustomer() {
        return customer;
    }

    public Address setCustomer(Restaurant customer) {
        this.customer = customer;
        return this;
    }
}