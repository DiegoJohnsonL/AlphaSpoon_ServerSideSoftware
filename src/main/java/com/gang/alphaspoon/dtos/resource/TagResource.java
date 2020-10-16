package com.gang.alphaspoon.dtos.resource;

import com.gang.alphaspoon.domain.entity.AuditModel;

public class TagResource extends AuditModel {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public TagResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TagResource setName(String name) {
        this.name = name;
        return this;
    }
}
