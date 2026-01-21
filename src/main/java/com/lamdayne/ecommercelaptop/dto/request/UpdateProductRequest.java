package com.lamdayne.ecommercelaptop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {
    @NotBlank(message = "PRODUCT_NAME_BLANK")
    private String name;

    @NotNull(message = "PRODUCT_PRICE_NULL")
    private Double basePrice;

    @NotNull(message = "PRODUCT_PRICE_NULL")
    private Double salePrice;

    @NotNull(message = "PRODUCT_STOCK_NULL")
    private Long stock;

    @NotBlank(message = "PRODUCT_DESCRIPTION_BLANK")
    private String description;

    @NotNull(message = "PRODUCT_STATUS_NULL")
    private int status;

    @NotNull(message = "CATEGORY_ID_NULL")
    private int categoryId;

    @NotNull(message = "BRAND_ID_NULL")
    private int brandId;
}
