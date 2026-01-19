package com.lamdayne.ecommercelaptop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "FIRST_NAME_BLANK")
    private String fullName;

    @Size(min = 8, message = "INVALID_PASSWORD")
    private String password;

    @NotBlank(message = "EMAIL_BLANK")
    private String email;

    @Size(min = 10, message = "INVALID_PHONE")
    private String phone;

    private Date dob;
}
