package com.lamdayne.ecommercelaptop.service.impl;

import com.lamdayne.ecommercelaptop.constant.SessionConstant;
import com.lamdayne.ecommercelaptop.dto.request.CreateOrderDTO;
import com.lamdayne.ecommercelaptop.entity.Order;
import com.lamdayne.ecommercelaptop.entity.OrderDetail;
import com.lamdayne.ecommercelaptop.entity.Product;
import com.lamdayne.ecommercelaptop.entity.User;
import com.lamdayne.ecommercelaptop.mapper.ProductMapper;
import com.lamdayne.ecommercelaptop.repository.OrderRepository;
import com.lamdayne.ecommercelaptop.repository.ProductRepository;
import com.lamdayne.ecommercelaptop.service.OrderDetailService;
import com.lamdayne.ecommercelaptop.service.OrderService;
import com.lamdayne.ecommercelaptop.service.ProductService;
import com.lamdayne.ecommercelaptop.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final SessionUtil sessionUtil;
    private final ProductRepository productRepository;
    private final OrderDetailService orderDetailService;

    @Override
    public Order createOrder(CreateOrderDTO createOrderDTO) {
        Order order = new Order();
        order.setAddress(createOrderDTO.getAddress());
        order.setCreatedAt(new Date());
        order.setStatus(0);
        order.setTotalPrice(createOrderDTO.getTotalPrice());
        order.setUser((User) sessionUtil.get(SessionConstant.SESSION_USER));
        order = orderRepository.save(order);

        Product product = productRepository.findById(createOrderDTO.getProductId()).orElse(null);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setPrice(product.getSalePrice());
        orderDetail.setQuantity(1);
        orderDetailService.createOrderDetail(orderDetail);

        return order;
    }

    @Override
    public Order updateOrderStatus(String orderId, Integer orderStatus) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(orderStatus);
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}
