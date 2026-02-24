package com.lamdayne.ecommercelaptop.dto.request;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {
    private String address;
    private List<String> productIds;
    private List<Integer> quantities;
    private String email;
    private boolean fromCart;
}
