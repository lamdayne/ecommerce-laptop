package com.lamdayne.ecommercelaptop.controller;


import com.lamdayne.ecommercelaptop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ProductDetailController {

    private final ProductService productService;

    @GetMapping("/detail/{productId}")
    public String detail(@PathVariable String productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "detail";
    }
}
