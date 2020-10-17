package com.gang.alphaspoon.dtos.request;

import java.util.List;

public class OrderRequest {
    private List<OrderLineRequest> lines;
    private Double total;
}
