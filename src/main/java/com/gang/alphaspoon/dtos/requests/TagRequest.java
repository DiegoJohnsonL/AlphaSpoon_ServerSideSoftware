package com.gang.alphaspoon.dtos.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TagRequest {
    @NotNull
    @Size(max = 100)
    private String name;
    public String getName() {
        return name;
    }

    public TagRequest setName(String name) {
        this.name = name;
        return this;
    }
}
