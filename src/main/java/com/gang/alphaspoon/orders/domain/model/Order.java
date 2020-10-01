package com.gang.alphaspoon.orders.domain.model;


import com.gang.alphaspoon.model.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
