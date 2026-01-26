package com.lamdayne.ecommercelaptop.controller;

import com.lamdayne.ecommercelaptop.dto.request.CreateUserRequest;
import com.lamdayne.ecommercelaptop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "/home/login";
    }

    @GetMapping("/register")
    public String register(){
        return "/home/register";
    }

    @PostMapping("/register")
    public String register(CreateUserRequest userInfo, Model model) {
        userService.createUser(userInfo);
        return "redirect:/auth/login";
    }
}
