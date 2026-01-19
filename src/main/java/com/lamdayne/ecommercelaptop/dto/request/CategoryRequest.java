package com.lamdayne.ecommercelaptop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    @NotBlank(message = "CATEGORY_NAME_BLANK")
    private String name;
}
