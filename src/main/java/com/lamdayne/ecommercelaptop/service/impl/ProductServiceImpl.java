package com.lamdayne.ecommercelaptop.service.impl;

import com.lamdayne.ecommercelaptop.dto.request.CreateProductRequest;
import com.lamdayne.ecommercelaptop.dto.request.UpdateProductRequest;
import com.lamdayne.ecommercelaptop.dto.response.ProductResponse;
import com.lamdayne.ecommercelaptop.entity.Brand;
import com.lamdayne.ecommercelaptop.entity.Category;
import com.lamdayne.ecommercelaptop.entity.Product;
import com.lamdayne.ecommercelaptop.exception.AppException;
import com.lamdayne.ecommercelaptop.exception.ErrorCode;
import com.lamdayne.ecommercelaptop.mapper.ProductMapper;
import com.lamdayne.ecommercelaptop.repository.BrandRepository;
import com.lamdayne.ecommercelaptop.repository.CategoryRepository;
import com.lamdayne.ecommercelaptop.repository.ProductRepository;
import com.lamdayne.ecommercelaptop.service.ProductService;
import com.lamdayne.ecommercelaptop.service.UploadImageFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final UploadImageFileService uploadImageFileService;

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        Product product = productMapper.toProduct(request);
        product.setBrand(brand);
        product.setCategory(category);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(String id, UpdateProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productMapper.updateProduct(product, request);

        if (!product.getCategory().getId().equals(request.getCategoryId())) {
            product.setCategory(
                    categoryRepository.findById(request.getCategoryId())
                            .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND)));
        }

        if (!product.getBrand().getId().equals(request.getBrandId())) {
            product.setBrand(brandRepository.findById(request.getBrandId())
                    .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND)));
        }

        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productRepository.delete(product);
    }

    @Override
    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return productMapper.toProductResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productMapper.toProductResponse(productRepository.findAll());
    }

    @Override
    public ProductResponse uploadImageProduct(String id, MultipartFile file) throws IOException {
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        product.setThumbnail(uploadImageFileService.uploadImageFile(file));
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public List<ProductResponse> getAllProductsByCategoryId(Integer categoryId) {
        return productMapper.toProductResponse(productRepository.getAllProductsByCategoryId(categoryId));
    }

    @Override
    public List<ProductResponse> getAllProductsByBrandId(Integer brandId) {
        return productMapper.toProductResponse(productRepository.getAllProductsByBrandId(brandId));
    }

    @Override
    public List<ProductResponse> getAllProductsByPrice(Double minPrice, Double maxPrice) {
        return productMapper.toProductResponse(productRepository.getAllProductsByPrice(minPrice, maxPrice));
    }

    @Override
    public List<ProductResponse> search(String keyword, Integer brandId, Integer categoryId) {
        return productMapper.toProductResponse(productRepository.search(keyword == null|| keyword.isBlank() ? "null" : keyword, brandId, categoryId));
    }
}
