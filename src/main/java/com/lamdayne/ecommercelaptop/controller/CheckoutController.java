package com.lamdayne.ecommercelaptop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {
    @GetMapping("/payment")
    public String payment() {
        return "checkout";
    }
}
