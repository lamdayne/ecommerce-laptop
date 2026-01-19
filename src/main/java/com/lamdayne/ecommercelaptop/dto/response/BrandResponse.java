package com.lamdayne.ecommercelaptop.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandResponse {
    private int id;
    private String name;
    private String imageUrl;
}
