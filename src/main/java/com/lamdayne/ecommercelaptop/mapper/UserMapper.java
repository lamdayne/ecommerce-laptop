package com.lamdayne.ecommercelaptop.mapper;

import com.lamdayne.ecommercelaptop.dto.request.CreateUserRequest;
import com.lamdayne.ecommercelaptop.dto.request.UpdateUserDTO;
import com.lamdayne.ecommercelaptop.dto.request.UpdateUserRequest;
import com.lamdayne.ecommercelaptop.dto.response.UserResponse;
import com.lamdayne.ecommercelaptop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(CreateUserRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UpdateUserRequest request); // map value từ updateUserRequest vào user
    List<UserResponse> toUserResponseList(List<User> users);
}
