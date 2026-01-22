package com.lamdayne.ecommercelaptop.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartResponse {
    private String id;
    private String userId;
    private String productId;
    private Integer quantity;
}
