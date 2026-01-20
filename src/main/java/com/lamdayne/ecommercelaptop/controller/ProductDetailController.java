package com.lamdayne.ecommercelaptop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductDetailController {
    @GetMapping("/detail")
    public String detail() {
        return "detail";
    }
}
