package com.lamdayne.ecommercelaptop.repository;

import com.lamdayne.ecommercelaptop.entity.Brand;
import com.lamdayne.ecommercelaptop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByStatus(int status);
    List<Product> findByCategory_Id(Integer categoryId);


}

