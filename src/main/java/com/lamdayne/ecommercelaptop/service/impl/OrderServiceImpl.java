package com.lamdayne.ecommercelaptop.service.impl;

import com.lamdayne.ecommercelaptop.constant.SessionConstant;
import com.lamdayne.ecommercelaptop.dto.request.CreateOrderDTO;
import com.lamdayne.ecommercelaptop.entity.Order;
import com.lamdayne.ecommercelaptop.entity.OrderDetail;
import com.lamdayne.ecommercelaptop.entity.Product;
import com.lamdayne.ecommercelaptop.entity.User;
import com.lamdayne.ecommercelaptop.exception.AppException;
import com.lamdayne.ecommercelaptop.exception.ErrorCode;
import com.lamdayne.ecommercelaptop.mapper.ProductMapper;
import com.lamdayne.ecommercelaptop.repository.OrderRepository;
import com.lamdayne.ecommercelaptop.repository.ProductRepository;
import com.lamdayne.ecommercelaptop.service.*;
import com.lamdayne.ecommercelaptop.util.SessionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Value("${spring.mail.username}")
    private String from;

    private final OrderRepository orderRepository;
    private final SessionUtil sessionUtil;
    private final ProductRepository productRepository;
    private final OrderDetailService orderDetailService;
    private final EmailService emailService;
    private final CartService cartService;

    @Override
    @Transactional
    public Order createOrder(CreateOrderDTO createOrderDTO) {
        Order order = new Order();
        order.setAddress(createOrderDTO.getAddress());
        order.setCreatedAt(new Date());
        order.setStatus(0);
        order.setUser((User) sessionUtil.get(SessionConstant.SESSION_USER));
        order.setTotalPrice(0d);
        Order savedOrder = orderRepository.save(order);

        List<String> productIds = createOrderDTO.getProductIds();
        List<Integer> quantities = createOrderDTO.getQuantities();
        double calculatedPrice = 0;

        User user = (User) sessionUtil.get(SessionConstant.SESSION_USER);

        for (int i = 0; i < productIds.size(); i++) {
            String pId = productIds.get(i);
            Integer quant = quantities.get(i);

            Product product = productRepository.findById(pId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
            product.setStock(product.getStock() - quant);
            productRepository.save(product);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(savedOrder);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(quant);
            orderDetail.setPrice(product.getSalePrice());
            orderDetailService.createOrderDetail(orderDetail);
            calculatedPrice += product.getSalePrice() * quant;
        }

        savedOrder.setTotalPrice(calculatedPrice);

        if (createOrderDTO.isFromCart()) {
            System.out.println("Xóa giỏ hàng user: " + user.getId());
            cartService.clearCartByUserId(user.getId(), productIds);
        }

        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String formattedPrice = currencyVN.format(savedOrder.getTotalPrice());

        String body = "<h3>Thông tin đơn hàng:</h3>" +
                "Địa chỉ: " + order.getAddress() + "<br/>" +
                "Tổng tiền: <b>" + formattedPrice + "</b>";
        String subject = "Đặt hàng thành công với mã đơn " + savedOrder.getId();
        sendEmail(subject, body, createOrderDTO.getEmail());

        return orderRepository.save(savedOrder);
    }

    @Override
    public Order updateOrderStatus(String orderId, Integer orderStatus) {
        Order order = orderRepository.findById(orderId).orElse(null);
        String subject = "Cập nhật trạng thái đơn hàng " + orderId;
        String status = "";
        switch (orderStatus) {
            case 0:
                status = "Chờ xử lý";
                break;
            case 1:
                status = "Đã xác nhận đơn";
                break;
            case 2:
                status = "Đang vận chuyển";
                break;
            case 3:
                status = "Hoàn thành";
                break;
            case 4:
                status = "Đã hủy";
                break;
            default:
                status = "";
        }
        if (order != null) {
            order.setStatus(orderStatus);

            String ordStatus = "<h1>Trạng thái đơn hàng của bạn: " + status + "</h1>";
//            sendEmail(subject, ordStatus, order.getAddress());

            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> getOrders(Pageable pageable, String orderId) {
        if (orderId == null || orderId.trim().isEmpty()) {
            return orderRepository.findAll(pageable);
        }
        return orderRepository.findByIdStartingWith(orderId, pageable);
    }

    @Override
    public Long countOrders() {
        return orderRepository.count();
    }

    @Override
    public Page<Order> getOrdersByUserId(Pageable pageable, String userId) {
        return orderRepository.findAllByUserId(userId, pageable);
    }

    private void sendEmail(String subject, String body, String to) {
        EmailService.Mail mail = EmailService.Mail.builder()
                .from(from)
                .to(to)
                .subject(subject)
                .body(body)
                .build();
        emailService.send(mail);
    }

}
