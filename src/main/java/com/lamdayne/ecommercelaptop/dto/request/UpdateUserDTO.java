package com.lamdayne.ecommercelaptop.dto.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
    private String fullName;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-mm-DD")
    private Date dob;
}
