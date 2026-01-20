package com.lamdayne.ecommercelaptop.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    private String id;
    private String name;
    private Double basePrice;
    private Double salePrice;
    private Long stock;
    private String thumbnail;
    private String description;
    private int status;
    private int categoryId;
    private int brandId;
}
