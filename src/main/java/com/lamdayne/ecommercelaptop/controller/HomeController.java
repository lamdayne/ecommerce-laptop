package com.lamdayne.ecommercelaptop.controller;


import com.lamdayne.ecommercelaptop.repository.CategoryRepository;
import com.lamdayne.ecommercelaptop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public String home(
        @RequestParam(value = "category", required = false) Integer categoryId,
        Model model) {

            List<Product> products;

            if (categoryId == null) {
                products = productRepository.findAll();
            } else {
                products = productRepository.findByCategory_Id(categoryId);
            }

        model.addAttribute("products",productRepository.findByStatus(1)) ;
        model.addAttribute("categories",categoryRepository.findAll());
            model.addAttribute("activeCategory", categoryId);

        return "home/page";
    }

    @GetMapping("/test")
    @ResponseBody
    public List<Product> test() {
        return productRepository.findAll();
    }
}
