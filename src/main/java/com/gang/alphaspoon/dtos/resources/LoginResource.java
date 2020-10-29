package com.gang.alphaspoon.dtos.resources;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResource {
    private CustomerResource user;
    private String token;
}
