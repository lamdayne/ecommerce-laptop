package com.lamdayne.ecommercelaptop.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {
    private Double totalPrice;
    private String address;
    private String productId;
    private String email;
}
