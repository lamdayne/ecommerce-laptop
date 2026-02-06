package com.lamdayne.ecommercelaptop.service;

import com.lamdayne.ecommercelaptop.dto.request.CreateUserRequest;
import com.lamdayne.ecommercelaptop.dto.request.UpdateUserDTO;
import com.lamdayne.ecommercelaptop.dto.request.UpdateUserRequest;
import com.lamdayne.ecommercelaptop.dto.response.UserResponse;
import com.lamdayne.ecommercelaptop.entity.User;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(String userId);
    UserResponse createUser(CreateUserRequest request);
    UserResponse updateUser(String userId, UpdateUserRequest request);
    void deleteUser(String userId);
    User getUserByEmail(String email);
    UserResponse updateUser(String userId, UpdateUserDTO userDTO);
    void changePassword(String userId, String newPassword);
}
