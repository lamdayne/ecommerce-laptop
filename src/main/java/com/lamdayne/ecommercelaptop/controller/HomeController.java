package com.lamdayne.ecommercelaptop.controller;


import com.lamdayne.ecommercelaptop.dto.response.ProductResponse;
import com.lamdayne.ecommercelaptop.service.CategoryService;
import com.lamdayne.ecommercelaptop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@RequiredArgsConstructor
@Controller
public class HomeController {

private final ProductService productService;
private final CategoryService categoryService;
    @GetMapping("/")
    public String home(
        @RequestParam(value = "category", required = false) Integer categoryId,
        Model model) {

            List<ProductResponse> products;

            if (categoryId == null) {
                products = productService.getAllProducts();
            } else {
                products = productService.getAllProductsByCategoryId(categoryId);
            }

        model.addAttribute("products",productService.getAllProducts()) ;
        model.addAttribute("categories",categoryService.getAllCategories());
            model.addAttribute("activeCategory", categoryId);

        return "home/page";
    }


}
