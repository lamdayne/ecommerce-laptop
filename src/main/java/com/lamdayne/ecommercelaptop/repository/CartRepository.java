package com.lamdayne.ecommercelaptop.repository;

import com.lamdayne.ecommercelaptop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    List<Cart> getAllCartsByUserId(String userId);
}
