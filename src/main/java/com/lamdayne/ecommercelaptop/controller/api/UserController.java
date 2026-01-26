package com.lamdayne.ecommercelaptop.controller.api;

import com.lamdayne.ecommercelaptop.dto.request.CreateUserRequest;
import com.lamdayne.ecommercelaptop.dto.request.UpdateUserRequest;
import com.lamdayne.ecommercelaptop.dto.response.ApiResponse;
import com.lamdayne.ecommercelaptop.dto.response.UserResponse;
import com.lamdayne.ecommercelaptop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .data(userService.getAllUsers())
                .build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUserById(@PathVariable String userId) {
        return ApiResponse.<UserResponse>builder()
                .data(userService.getUserById(userId))
                .build();
    }

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .data(userService.createUser(request))
                .build();
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody @Valid UpdateUserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .data(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    public ApiResponse<UserResponse> deleteUserById(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ApiResponse.<UserResponse>builder()
                .message("User has been deleted")
                .build();
    }

}
