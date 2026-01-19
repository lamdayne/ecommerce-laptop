package com.lamdayne.ecommercelaptop.service;

import com.lamdayne.ecommercelaptop.dto.request.CategoryRequest;
import com.lamdayne.ecommercelaptop.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse updateCategory(int id, CategoryRequest request);
    void deleteCategory(int id);
    CategoryResponse getCategoryById(int id);
    List<CategoryResponse> getAllCategories();
}
