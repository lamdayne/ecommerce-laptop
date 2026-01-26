package com.lamdayne.ecommercelaptop.controller.api;

import com.lamdayne.ecommercelaptop.dto.request.CategoryRequest;
import com.lamdayne.ecommercelaptop.dto.response.ApiResponse;
import com.lamdayne.ecommercelaptop.dto.response.CategoryResponse;
import com.lamdayne.ecommercelaptop.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .message("Category created")
                .data(categoryService.createCategory(categoryRequest))
                .build();
    }

    @PutMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> updateCategory(@PathVariable int categoryId, @RequestBody @Valid CategoryRequest categoryRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .message("Category updated")
                .data(categoryService.updateCategory(categoryId, categoryRequest))
                .build();
    }

    @DeleteMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> deleteCategory(@PathVariable int categoryId) {
        categoryService.deleteCategory(categoryId);
        return ApiResponse.<CategoryResponse>builder()
                .message("Category has been deleted")
                .build();
    }

    @GetMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> getCategoryById(@PathVariable int categoryId) {
        return ApiResponse.<CategoryResponse>builder()
                .data(categoryService.getCategoryById(categoryId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<CategoryResponse>> getAllCategories() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .data(categoryService.getAllCategories())
                .build();
    }

}
