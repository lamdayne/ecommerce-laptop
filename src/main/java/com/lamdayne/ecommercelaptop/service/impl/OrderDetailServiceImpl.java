package com.lamdayne.ecommercelaptop.service.impl;

import com.lamdayne.ecommercelaptop.entity.OrderDetail;
import com.lamdayne.ecommercelaptop.repository.OrderDetailRepository;
import com.lamdayne.ecommercelaptop.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
}
