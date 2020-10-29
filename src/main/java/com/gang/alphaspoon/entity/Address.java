package com.gang.alphaspoon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "address")
public class Address extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min=5, message= "El texto debe contener mínimo 3 caracteres")
    private String tDir;

    @Column(name="fecha principal", nullable = false, updatable = false)
    private LocalDateTime fPrincipal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;

    public Address(Long id, @Length(min = 5, message = "El texto debe contener mínimo 3 caracteres") String tDir, LocalDateTime fPrincipal, Customer customer) {
        this.id = id;
        this.tDir = tDir;
        this.fPrincipal = fPrincipal;
        this.customer = customer;
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getfPrincipal() {
        return fPrincipal;
    }

    public void setfPrincipal(LocalDateTime fPrincipal) {
        this.fPrincipal = fPrincipal;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}