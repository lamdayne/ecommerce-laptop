package com.lamdayne.ecommercelaptop.controller;


import com.lamdayne.ecommercelaptop.dto.response.ProductResponse;
import com.lamdayne.ecommercelaptop.service.BrandService;
import com.lamdayne.ecommercelaptop.service.CategoryService;
import com.lamdayne.ecommercelaptop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final BrandService brandService;

    @GetMapping("/")
    public String home( @RequestParam(defaultValue = "0") int page,
        @RequestParam(value = "category", required = false) Integer categoryId,
        Model model) {
        int size = 8;
        Page<ProductResponse> productPage;

        if (categoryId == null) {
            productPage = productService.getProducts(page, size);
        } else {
            productPage = productService.getProductsByCategory(categoryId, page, size);
        }

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("activeCategory", categoryId);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("category", categoryId);

        return "home/page";
    }



    @GetMapping("/searchproducts")
    public String List(@RequestParam(required = false) String keyword,
                       @RequestParam(required = false) Integer brandId,
                       @RequestParam(required = false) Integer categoryId,
                       Model model
    ) {
        model.addAttribute("products", productService.search(keyword, brandId, categoryId));
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("keyword", keyword);
        model.addAttribute("brandId", brandId);
        model.addAttribute("categoryId", categoryId);

        return "home/researchProducts";
    }



}
