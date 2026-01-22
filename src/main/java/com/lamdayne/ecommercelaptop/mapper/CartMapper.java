package com.lamdayne.ecommercelaptop.mapper;

import com.lamdayne.ecommercelaptop.dto.request.CreateCartRequest;
import com.lamdayne.ecommercelaptop.dto.request.UpdateCartRequest;
import com.lamdayne.ecommercelaptop.dto.response.CartResponse;
import com.lamdayne.ecommercelaptop.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart toCart(CreateCartRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "product.id", target = "productId")
    CartResponse toCartResponse(Cart cart);
    void updateCart(@MappingTarget Cart cart, UpdateCartRequest request);
    List<CartResponse> toCartResponseList(List<Cart> carts);
}
