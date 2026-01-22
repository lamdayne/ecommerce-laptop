package com.lamdayne.ecommercelaptop.controller;

import com.lamdayne.ecommercelaptop.dto.request.CreateCartRequest;
import com.lamdayne.ecommercelaptop.dto.request.UpdateCartRequest;
import com.lamdayne.ecommercelaptop.dto.response.ApiResponse;
import com.lamdayne.ecommercelaptop.dto.response.CartResponse;
import com.lamdayne.ecommercelaptop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ApiResponse<CartResponse> createCart(@RequestBody CreateCartRequest request) {
        return ApiResponse.<CartResponse>builder()
                .data(cartService.createCart(request))
                .build();
    }

    @PutMapping("/{cartId}")
    public ApiResponse<CartResponse> updateCart(@PathVariable String cartId, @RequestBody UpdateCartRequest request) {
        return ApiResponse.<CartResponse>builder()
                .data(cartService.updateCart(cartId, request))
                .build();
    }

    @DeleteMapping("/{cartId}")
    public ApiResponse<CartResponse> deleteCart(@PathVariable String cartId) {
        cartService.deleteCart(cartId);
        return ApiResponse.<CartResponse>builder()
                .message("Cart deleted")
                .build();
    }

    @GetMapping("/{cartId}")
    public ApiResponse<CartResponse> getCartById(@PathVariable String cartId) {
        return ApiResponse.<CartResponse>builder()
                .data(cartService.getCartById(cartId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<CartResponse>> getAllCarts() {
        return ApiResponse.<List<CartResponse>>builder()
                .data(cartService.getAllCarts())
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<CartResponse>> getAllCartsByUserId(@RequestParam String userId) {
        return ApiResponse.<List<CartResponse>>builder()
                .data(cartService.getAllCartsByUserId(userId))
                .build();
    }

}
