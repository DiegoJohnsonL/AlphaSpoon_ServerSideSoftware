package com.gang.alphaspoon.dtos.resources;

import com.gang.alphaspoon.entity.AuditModel;

import java.util.List;

public class OrderResource extends AuditModel {
    private Long id;
    private String regDate;
    private List<OrderLineResource> lines;
    private Double total;

    public Long getId() {
        return id;
    }

    public OrderResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRegDate() {
        return regDate;
    }

    public OrderResource setRegDate(String regDate) {
        this.regDate = regDate;
        return this;
    }

    public List<OrderLineResource> getLines() {
        return lines;
    }

    public OrderResource setLines(List<OrderLineResource> lines) {
        this.lines = lines;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public OrderResource setTotal(Double total) {
        this.total = total;
        return this;
    }
}
