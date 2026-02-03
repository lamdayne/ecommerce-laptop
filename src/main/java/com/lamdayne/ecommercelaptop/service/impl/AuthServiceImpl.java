package com.lamdayne.ecommercelaptop.service.impl;

import com.lamdayne.ecommercelaptop.entity.User;
import com.lamdayne.ecommercelaptop.mapper.UserMapper;
import com.lamdayne.ecommercelaptop.service.AuthService;
import com.lamdayne.ecommercelaptop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public User login(String email, String password) {
        return userService.getUserByEmail(email);
    }

}
