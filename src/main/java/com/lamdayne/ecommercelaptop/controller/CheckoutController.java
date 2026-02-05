package com.lamdayne.ecommercelaptop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckoutController {
    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    @PostMapping("/checkout")
    public String checkout(Model model, @RequestParam("productId") String productId) {
        return "checkout";
    }
}
