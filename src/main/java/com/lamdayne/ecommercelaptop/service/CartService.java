package com.lamdayne.ecommercelaptop.service;

import com.lamdayne.ecommercelaptop.dto.request.CreateCartRequest;
import com.lamdayne.ecommercelaptop.dto.request.UpdateCartRequest;
import com.lamdayne.ecommercelaptop.dto.response.CartResponse;

import java.util.List;

public interface CartService {
    CartResponse createCart(CreateCartRequest request);
    CartResponse updateCart(String cartId, UpdateCartRequest request);
    void deleteCart(String cartId);
    CartResponse getCartById(String cartId);
    List<CartResponse> getAllCarts();
    List<CartResponse> getAllCartsByUserId(String userId);
}
