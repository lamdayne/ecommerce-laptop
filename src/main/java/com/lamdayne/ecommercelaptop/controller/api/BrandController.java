package com.lamdayne.ecommercelaptop.controller;

import com.cloudinary.Api;
import com.lamdayne.ecommercelaptop.dto.request.BrandRequest;
import com.lamdayne.ecommercelaptop.dto.response.ApiResponse;
import com.lamdayne.ecommercelaptop.dto.response.BrandResponse;
import com.lamdayne.ecommercelaptop.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public ApiResponse<BrandResponse> createBrand(@RequestBody @Valid BrandRequest brandRequest) {
        return ApiResponse.<BrandResponse>builder()
                .data(brandService.createBrand(brandRequest))
                .build();
    }

    @PostMapping("/upload-image/{brandId}")
    public ApiResponse<BrandResponse> uploadImageBrand(@PathVariable int brandId,
                                                       @RequestParam("file") MultipartFile file
    ) throws IOException {
        return ApiResponse.<BrandResponse>builder()
                .data(brandService.uploadImageBrand(brandId, file))
                .build();
    }

    @PutMapping("/{brandId}")
    public ApiResponse<BrandResponse> updateBrand(@PathVariable int brandId, @RequestBody @Valid BrandRequest brandRequest) {
        return ApiResponse.<BrandResponse>builder()
                .data(brandService.updateBrand(brandId, brandRequest))
                .build();
    }

    @DeleteMapping("/{brandId}")
    public ApiResponse<BrandResponse> deleteBrand(@PathVariable int brandId) {
        brandService.deleteBrand(brandId);
        return ApiResponse.<BrandResponse>builder()
                .message("Brand deleted successfully")
                .build();
    }

    @GetMapping("/{brandId}")
    public ApiResponse<BrandResponse> getBrand(@PathVariable int brandId) {
        return ApiResponse.<BrandResponse>builder()
                .data(brandService.getBrandById(brandId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<BrandResponse>> getAllBrands() {
        return ApiResponse.<List<BrandResponse>>builder()
                .data(brandService.getAllBrands())
                .build();
    }

}
