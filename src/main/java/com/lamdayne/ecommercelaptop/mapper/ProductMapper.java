package com.lamdayne.ecommercelaptop.mapper;

import com.lamdayne.ecommercelaptop.dto.request.CreateProductRequest;
import com.lamdayne.ecommercelaptop.dto.request.UpdateProductRequest;
import com.lamdayne.ecommercelaptop.dto.response.ProductResponse;
import com.lamdayne.ecommercelaptop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(CreateProductRequest request);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "brand.id", target = "brandId")
    ProductResponse toProductResponse(Product product);
    void updateProduct(@MappingTarget Product product, UpdateProductRequest request);
    List<ProductResponse> toProductResponse(List<Product> products);
    default Page<ProductResponse> toProductResponse(Page<Product> page) {
        return page.map(this::toProductResponse);
    }
}
