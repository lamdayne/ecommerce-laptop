package com.lamdayne.ecommercelaptop.repository;

import com.lamdayne.ecommercelaptop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> getAllProductsByCategoryId(Integer categoryId);
    List<Product> getAllProductsByBrandId(Integer brandId);
}
