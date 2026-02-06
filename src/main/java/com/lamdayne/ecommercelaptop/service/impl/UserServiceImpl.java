package com.lamdayne.ecommercelaptop.service.impl;

import com.lamdayne.ecommercelaptop.constant.Role;
import com.lamdayne.ecommercelaptop.dto.request.CreateUserRequest;
import com.lamdayne.ecommercelaptop.dto.request.UpdateUserDTO;
import com.lamdayne.ecommercelaptop.dto.request.UpdateUserRequest;
import com.lamdayne.ecommercelaptop.dto.response.UserResponse;
import com.lamdayne.ecommercelaptop.entity.User;
import com.lamdayne.ecommercelaptop.exception.AppException;
import com.lamdayne.ecommercelaptop.exception.ErrorCode;
import com.lamdayne.ecommercelaptop.mapper.UserMapper;
import com.lamdayne.ecommercelaptop.repository.UserRepository;
import com.lamdayne.ecommercelaptop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> getAllUsers() {
        return userMapper.toUserResponseList(userRepository.findAll());
    }

    @Override
    public UserResponse getUserById(String userId) {
        return userMapper.toUserResponse(userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTS);
        } else if (userRepository.existsByPhone(request.getPhone())) {
            throw new AppException(ErrorCode.PHONE_EXISTS);
        }

        User user = userMapper.toUser(request);
        user.setRole(Role.USER);

        try {
            user = userRepository.save(user);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(String userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserResponse updateUser(String userId, UpdateUserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        user.setFullName(userDTO.getFullName());
        user.setPhone(userDTO.getPhone());
        user.setDob(userDTO.getDob());
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void changePassword(String userId, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        user.setPassword(newPassword);
        userRepository.save(user);
    }

}
