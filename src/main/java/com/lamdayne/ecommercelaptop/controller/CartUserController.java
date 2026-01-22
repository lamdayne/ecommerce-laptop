package com.lamdayne.ecommercelaptop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartUserController {

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("title", "Giỏ hàng test");
        model.addAttribute("message", "Trang giỏ hàng đang hoạt động!");
        return "cart";
    }
}
