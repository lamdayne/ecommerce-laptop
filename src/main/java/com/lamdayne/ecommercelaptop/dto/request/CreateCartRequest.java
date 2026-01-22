package com.lamdayne.ecommercelaptop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCartRequest {
    @NotBlank(message = "CART_USER_ID_BLANK")
    private String userId;

    @NotBlank(message = "CART_PRODUCT_ID_BLANK")
    private String productId;

    @NotNull(message = "PRODUCT_QUANTITY_NULL")
    private Integer quantity;
}
