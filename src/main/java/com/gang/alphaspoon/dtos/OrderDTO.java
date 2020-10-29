package com.gang.alphaspoon.dtos;

import com.gang.alphaspoon.dtos.resources.CustomerResource;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String regDate;
    private List<OrderLineDTO> lines;
    private Double total;
    private CustomerResource customer;
}