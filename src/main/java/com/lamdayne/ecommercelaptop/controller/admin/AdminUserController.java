package com.lamdayne.ecommercelaptop.controller.admin;


import com.lamdayne.ecommercelaptop.dto.request.UpdateUserDTO;
import com.lamdayne.ecommercelaptop.dto.response.UserResponse;
import com.lamdayne.ecommercelaptop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/account")
public class AdminUserController {

    private final UserService userService;
    
    @GetMapping
    public String redirectToList() {
        return "redirect:/admin/account/list";
    }

    @GetMapping("/list")
    public String list(
            @RequestParam(defaultValue = "1") int page,
            Model model
    ) {
        int pageSize = 5;
        int pageIndex = page - 1;

        List<UserResponse> allUsers = userService.getAllUsers();

        int start = Math.min(pageIndex * pageSize, allUsers.size());
        int end = Math.min(start + pageSize, allUsers.size());

        Page<UserResponse> pageData = new PageImpl<>(
                allUsers.subList(start, end),
                PageRequest.of(pageIndex, pageSize),
                allUsers.size()
        );

        model.addAttribute("page", pageData);
        return "admin/account/account-list";
    }

    @GetMapping("/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/account/account-form-update";
    }

    @PostMapping("/{id}")
    public String update(
            @PathVariable String id,
            UpdateUserDTO dto,
            RedirectAttributes redirect
    ) {
        userService.updateUser(id, dto);
        redirect.addFlashAttribute("message", "Cập nhật tài khoản thành công");
        return "redirect:/admin/account";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable String id,
            RedirectAttributes redirect
    ) {
        userService.deleteUser(id);
        redirect.addFlashAttribute("message", "Xóa tài khoản thành công");
        return "redirect:/admin/account";
    }
}

