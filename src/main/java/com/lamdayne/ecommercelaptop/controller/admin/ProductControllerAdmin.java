package com.lamdayne.ecommercelaptop.controller.admin;

import com.lamdayne.ecommercelaptop.dto.request.CreateProductRequest;
import com.lamdayne.ecommercelaptop.dto.request.UpdateProductRequest;
import com.lamdayne.ecommercelaptop.service.BrandService;
import com.lamdayne.ecommercelaptop.service.CategoryService;
import com.lamdayne.ecommercelaptop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ProductControllerAdmin {
    private final ProductService productService;
    private final BrandService brandService;
    private final CategoryService categoryService;

    @GetMapping("/admin/product")
    public String product(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/admin/product/product-form-create";
    }

    @GetMapping("/admin/product/{productId}")
    public String product(Model model, @PathVariable("productId") String productId) {
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("product", productService.getProductById(productId));
        return "/admin/product/product-form-update";
    }

    @GetMapping("/admin/list-product")
    public String listProduct(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "/admin/product/product-list";
    }

    @PostMapping("/admin/product")
    public String addProduct(CreateProductRequest productInfo, @RequestParam("imageFile") MultipartFile file) throws IOException {
        String productId = productService.createProduct(productInfo).getId();
        productService.uploadImageProduct(productId, file);
        return "redirect:/admin/product";
    }

    @PostMapping("/admin/product/{productId}")
    public String updateProduct(UpdateProductRequest productInfo, @PathVariable String productId) {
        productService.updateProduct(productId, productInfo);
        return "redirect:/admin/product";
    }
}
