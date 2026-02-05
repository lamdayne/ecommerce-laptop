package com.lamdayne.ecommercelaptop.repository;

import com.lamdayne.ecommercelaptop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    @Query("""
        SELECT p FROM Product p
        WHERE (:keyword IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
             OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')))
        AND (:brandId IS NULL OR p.brand.id = :brandId)
        AND (:categoryId IS NULL OR p.category.id = :categoryId)
        AND (p.salePrice BETWEEN :minPrice AND :maxPrice)
    """)
    List<Product> search(
            @Param("keyword") String keyword,
            @Param("brandId") Integer brandId,
            @Param("categoryId") Integer categoryId,
            @Param("minPrice") Long minPrice,
            @Param("maxPrice") Long maxPrice
    );
    Page<Product> findAll(Pageable pageable);

    Page<Product> findByCategoryId(Integer categoryId, Pageable pageable);
}
