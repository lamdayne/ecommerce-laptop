package com.lamdayne.ecommercelaptop.controller.admin;

import com.lamdayne.ecommercelaptop.dto.request.CreateProductRequest;
import com.lamdayne.ecommercelaptop.dto.request.UpdateProductRequest;
import com.lamdayne.ecommercelaptop.entity.Product;
import com.lamdayne.ecommercelaptop.repository.ProductRepository;
import com.lamdayne.ecommercelaptop.service.BrandService;
import com.lamdayne.ecommercelaptop.service.CategoryService;
import com.lamdayne.ecommercelaptop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/product")
public class ProductControllerAdmin {

    private final ProductService productService;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    @GetMapping
    public String product(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/admin/product/product-form-create";
    }

    @GetMapping("/{productId}")
    public String product(Model model, @PathVariable("productId") String productId) {
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("product", productService.getProductById(productId));
        return "/admin/product/product-form-update";
    }

    @GetMapping("/list")
    public String listProduct(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 4);
        Page<Product> productPage = productRepository.findAll(pageable);
        model.addAttribute("page", productPage);
        return "/admin/product/product-list";
    }

    @PostMapping
    public String addProduct(CreateProductRequest productInfo,
                             @RequestParam("imageFile") MultipartFile file,
                             RedirectAttributes redirectAttributes
    ) throws IOException {
        String productId = productService.createProduct(productInfo).getId();
        productService.uploadImageProduct(productId, file);
        redirectAttributes.addFlashAttribute("message", "Thêm sản phẩm thành công");
        return "redirect:/admin/product";
    }

    @PostMapping("/{productId}")
    public String updateProduct(UpdateProductRequest productInfo, @PathVariable String productId) {
        productService.updateProduct(productId, productInfo);
        return "redirect:/admin/product";
    }
}
