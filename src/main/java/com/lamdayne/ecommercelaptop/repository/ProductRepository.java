package com.lamdayne.ecommercelaptop.repository;

import com.lamdayne.ecommercelaptop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> getAllProductsByCategoryId(Integer categoryId);
    List<Product> getAllProductsByBrandId(Integer brandId);

    @Query("SELECT p FROM Product p WHERE p.salePrice >= :minPrice AND p.salePrice <= :maxPrice")
    List<Product> getAllProductsByPrice(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);
}
