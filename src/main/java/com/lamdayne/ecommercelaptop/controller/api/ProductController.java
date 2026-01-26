package com.lamdayne.ecommercelaptop.controller.api;

import com.lamdayne.ecommercelaptop.dto.request.CreateProductRequest;
import com.lamdayne.ecommercelaptop.dto.request.UpdateProductRequest;
import com.lamdayne.ecommercelaptop.dto.response.ApiResponse;
import com.lamdayne.ecommercelaptop.dto.response.ProductResponse;
import com.lamdayne.ecommercelaptop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@RequestBody @Valid CreateProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .data(productService.createProduct(request))
                .build();
    }

    @PostMapping("/upload-image/{productId}")
    public ApiResponse<ProductResponse> uploadImageFile(@PathVariable String productId, @RequestParam("file") MultipartFile file) throws IOException {
        return ApiResponse.<ProductResponse>builder()
                .data(productService.uploadImageProduct(productId, file))
                .build();
    }

    @PutMapping("/{productId}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable String productId, @RequestBody @Valid UpdateProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .data(productService.updateProduct(productId, request))
                .build();
    }

    @DeleteMapping("/{productId}")
    public ApiResponse<ProductResponse> deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return ApiResponse.<ProductResponse>builder()
                .message("Product deleted")
                .build();
    }

    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable String productId) {
        return ApiResponse.<ProductResponse>builder()
                .data(productService.getProductById(productId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ProductResponse>> getAllProducts() {
        return ApiResponse.<List<ProductResponse>>builder()
                .data(productService.getAllProducts())
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<ProductResponse>> getAllProductsByCategoryId(@RequestParam Integer categoryId) {
        return ApiResponse.<List<ProductResponse>>builder()
                .data(productService.getAllProductsByCategoryId(categoryId))
                .build();
    }

    @GetMapping("/search/category/{categoryId}")
    public ApiResponse<List<ProductResponse>> getProductsByCategoryId(@PathVariable Integer categoryId) {
        return ApiResponse.<List<ProductResponse>>builder()
                .data(productService.getAllProductsByCategoryId(categoryId))
                .build();
    }

    @GetMapping("/search/brand/{brandId}")
    public ApiResponse<List<ProductResponse>> getAllProductsByBrandId(@PathVariable Integer brandId) {
        return ApiResponse.<List<ProductResponse>>builder()
                .data(productService.getAllProductsByBrandId(brandId))
                .build();
    }

    @GetMapping("/search/price")
    public ApiResponse<List<ProductResponse>> getAllProductsByPrice(@RequestParam("minPrice") Double minPrice, @RequestParam("maxPrice") Double maxPrice) {
        return ApiResponse.<List<ProductResponse>>builder()
                .data(productService.getAllProductsByPrice(minPrice, maxPrice))
                .build();
    }
}
