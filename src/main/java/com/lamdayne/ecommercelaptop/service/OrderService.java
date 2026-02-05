package com.lamdayne.ecommercelaptop.service;

import com.lamdayne.ecommercelaptop.dto.request.CreateOrderDTO;
import com.lamdayne.ecommercelaptop.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Order createOrder(CreateOrderDTO createOrderDTO);
    Order updateOrderStatus(String orderId, Integer orderStatus);
    Page<Order> getOrders(Pageable pageable);
}
