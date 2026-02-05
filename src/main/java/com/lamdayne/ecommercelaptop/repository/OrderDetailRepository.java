package com.lamdayne.ecommercelaptop.repository;

import com.lamdayne.ecommercelaptop.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}
