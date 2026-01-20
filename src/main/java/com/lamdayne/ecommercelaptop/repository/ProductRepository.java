package com.lamdayne.ecommercelaptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByStatus(int status);
    List<Product> findByCategory_Id(Integer categoryId);


}

