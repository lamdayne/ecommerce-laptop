package com.lamdayne.ecommercelaptop.controller;

import com.lamdayne.ecommercelaptop.constant.SessionConstant;
import com.lamdayne.ecommercelaptop.dto.request.CreateUserRequest;
import com.lamdayne.ecommercelaptop.dto.request.UpdateUserDTO;
import com.lamdayne.ecommercelaptop.dto.response.UserResponse;
import com.lamdayne.ecommercelaptop.entity.User;
import com.lamdayne.ecommercelaptop.mapper.UserMapper;
import com.lamdayne.ecommercelaptop.service.AuthService;
import com.lamdayne.ecommercelaptop.service.UserService;
import com.lamdayne.ecommercelaptop.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final SessionUtil session;
    private final AuthService authService;

    @GetMapping("/login")
    public String login(){
        return "/home/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        RedirectAttributes redirectAttributes
    ) {
        User user = authService.login(email, password);

        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Tài khoản không tồn lại");
        } else if (!user.getPassword().equals(password)) {
            redirectAttributes.addFlashAttribute("message", "Sai mật khẩu");
        } else {
            session.set(SessionConstant.SESSION_USER, user);
            redirectAttributes.addFlashAttribute("loginSuccess", "Đăng nhập thành công");
        }

        String securityUri = (String) session.get(SessionConstant.SECURITY_URI);
        if (securityUri != null) {
            return "redirect:" + securityUri;
        }
        return "redirect:/auth/login";
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

    @GetMapping("/my-info")
    public String myInfo(Model model){
        model.addAttribute("user", session.get(SessionConstant.SESSION_USER));
        return "/home/my-info";
    }

    @GetMapping("/logout")
    public String logout(){
        session.remove(SessionConstant.SESSION_USER);
        return "redirect:/";
    }

    @PostMapping("/change-info/{userId}")
    public String changeInfo(Model model,
                             @PathVariable String userId,
                             UpdateUserDTO userDTO
    ){
        UserResponse userResponse = userService.updateUser(userId, userDTO);
        session.set(SessionConstant.SESSION_USER, userService.getUserByEmail(userResponse.getEmail()));
        return "redirect:/auth/my-info";
    }

}
