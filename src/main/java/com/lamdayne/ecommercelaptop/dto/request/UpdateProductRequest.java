package com.lamdayne.ecommercelaptop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {
    @NotBlank(message = "PRODUCT_NAME_BLANK")
    private String name;

    @NotBlank(message = "PRODUCT_PRICE_BLANK")
    private Double basePrice;

    @NotBlank(message = "PRODUCT_PRICE_BLANK")
    private Double salePrice;

    @NotBlank(message = "PRODUCT_STOCK_BLANK")
    private Long stock;

    @NotBlank(message = "PRODUCT_DESCRIPTION_BLANK")
    private String description;

    @NotBlank(message = "PRODUCT_STATUS_BLANK")
    private int status;

    @NotBlank(message = "CATEGORY_ID_BLANK")
    private int categoryId;

    @NotBlank(message = "BRAND_ID_BLANK")
    private int brandId;
}
