package com.lamdayne.ecommercelaptop.controller;

import com.lamdayne.ecommercelaptop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CartUserController {

    private final CartService cartService;

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("title", "Giỏ hàng test");
        model.addAttribute("message", "Trang giỏ hàng đang hoạt động!");
        return "cart";
    }

    @PostMapping("/add-to-cart")
    public String cartPost(Model model, @RequestParam("productId") String productId) {
        cartService.addToCart(productId);
        return "redirect:/detail/" + productId;
    }
}
