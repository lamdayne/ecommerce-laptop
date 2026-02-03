package com.lamdayne.ecommercelaptop.service;

import com.lamdayne.ecommercelaptop.dto.request.CreateProductRequest;
import com.lamdayne.ecommercelaptop.dto.request.UpdateProductRequest;
import com.lamdayne.ecommercelaptop.dto.response.CategoryResponse;
import com.lamdayne.ecommercelaptop.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductResponse createProduct(CreateProductRequest request);
    ProductResponse updateProduct(String id, UpdateProductRequest request);
    void deleteProduct(String id);
    ProductResponse getProductById(String id);
    List<ProductResponse> getAllProducts();
    ProductResponse uploadImageProduct(String id, MultipartFile file) throws IOException;
    List<ProductResponse> getAllProductsByCategoryId(Integer categoryId);
    List<ProductResponse> getAllProductsByBrandId(Integer brandId);
    List<ProductResponse> getAllProductsByPrice(Double minPrice, Double maxPrice);
    List<ProductResponse> search(String keyword,Integer brandId,Integer categoryId);
    Page<ProductResponse> getProducts(int page, int size);
    Page<ProductResponse> getProductsByCategory(Integer categoryId, int page, int size);
}
