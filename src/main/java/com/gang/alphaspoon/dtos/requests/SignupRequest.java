package com.gang.alphaspoon.dtos.requests;

import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String email;
    @Length(min = 6, max = 16, message = "Password needs to have between 3 to 50 characters")
    private String password;
}
