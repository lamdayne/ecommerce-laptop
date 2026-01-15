package com.lamdayne.ecommercelaptop.repository;

import com.lamdayne.ecommercelaptop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
