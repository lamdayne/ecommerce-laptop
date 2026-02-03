package com.lamdayne.ecommercelaptop.service;

import com.lamdayne.ecommercelaptop.entity.User;

public interface AuthService {
    User login(String email, String password);
}
