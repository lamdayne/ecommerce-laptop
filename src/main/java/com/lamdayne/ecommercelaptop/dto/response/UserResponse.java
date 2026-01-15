package com.lamdayne.ecommercelaptop.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private String id;
    private String fullName;
    private String password;
    private String email;
    private String phone;
    private Date dob;
    private Boolean role;
}
