package com.lamdayne.ecommercelaptop.service.impl;

import com.lamdayne.ecommercelaptop.constant.SessionConstant;
import com.lamdayne.ecommercelaptop.dto.request.CreateCartRequest;
import com.lamdayne.ecommercelaptop.dto.request.UpdateCartRequest;
import com.lamdayne.ecommercelaptop.dto.response.CartResponse;
import com.lamdayne.ecommercelaptop.entity.Cart;
import com.lamdayne.ecommercelaptop.entity.Product;
import com.lamdayne.ecommercelaptop.entity.User;
import com.lamdayne.ecommercelaptop.exception.AppException;
import com.lamdayne.ecommercelaptop.exception.ErrorCode;
import com.lamdayne.ecommercelaptop.mapper.CartMapper;
import com.lamdayne.ecommercelaptop.repository.CartRepository;
import com.lamdayne.ecommercelaptop.repository.ProductRepository;
import com.lamdayne.ecommercelaptop.repository.UserRepository;
import com.lamdayne.ecommercelaptop.service.CartService;
import com.lamdayne.ecommercelaptop.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final SessionUtil sessionUtil;

    @Override
    public CartResponse createCart(CreateCartRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        Cart cart = cartMapper.toCart(request);
        cart.setUser(user);
        cart.setProduct(product);
        return cartMapper.toCartResponse(cartRepository.save(cart));
    }

    @Override
    public CartResponse updateCart(String cartId, UpdateCartRequest request) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
        cartMapper.updateCart(cart, request);
        return cartMapper.toCartResponse(cartRepository.save(cart));
    }

    @Override
    public void deleteCart(String cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public CartResponse getCartById(String cartId) {
        return cartMapper.toCartResponse(cartRepository.findById(cartId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND)));
    }

    @Override
    public List<CartResponse> getAllCarts() {
        return cartMapper.toCartResponseList(cartRepository.findAll());
    }

    @Override
    public List<CartResponse> getAllCartsByUserId(String userId) {
        return cartMapper.toCartResponseList(cartRepository.getAllCartsByUserId(userId));
    }

    @Override
    public CartResponse addToCart(String productId) {
        Cart cart = new Cart();
        cart.setUser((User) sessionUtil.get(SessionConstant.SESSION_USER));
        cart.setProduct(productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND)));
        cart.setQuantity(1);
        return cartMapper.toCartResponse(cartRepository.save(cart));
    }
}
