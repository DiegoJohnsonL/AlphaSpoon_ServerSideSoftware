package com.gang.alphaspoon.orders.dto.save;

import com.gang.alphaspoon.restaurants.dto.save.SaveProductResource;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SaveOrderResource {
    private List<SaveOrderLineResource> lines;
    private Double total;
}
