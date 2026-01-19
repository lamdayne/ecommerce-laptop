package com.lamdayne.ecommercelaptop.mapper;

import com.lamdayne.ecommercelaptop.dto.request.CategoryRequest;
import com.lamdayne.ecommercelaptop.dto.response.CategoryResponse;
import com.lamdayne.ecommercelaptop.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest request);
    CategoryResponse toCategoryResponse(Category category);
    void updateCategory(@MappingTarget Category category, CategoryRequest request);
    List<CategoryResponse> toCategoryResponseList(List<Category> categoryList);
}
