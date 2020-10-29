package com.gang.alphaspoon.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Builder
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

    //@ManyToOne(optional = false)
    //@JoinColumn(name = "customer_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    //private Customer customer;

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
}
