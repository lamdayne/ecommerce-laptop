package com.lamdayne.ecommercelaptop.repository;

import com.lamdayne.ecommercelaptop.entity.Cart;
import com.lamdayne.ecommercelaptop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    List<Cart> getAllCartsByUserId(String userId);

    List<Cart> findByUser(User user);

    @Modifying
    @Query(value = "DELETE FROM Cart c WHERE c.id = :cartId AND c.product.id = :productId")
    void deleteProductFromCart(@Param("cartId") String cartId, @Param("productId") String productId);
}
