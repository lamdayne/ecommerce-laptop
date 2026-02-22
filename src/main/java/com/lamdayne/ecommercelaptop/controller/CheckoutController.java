package com.lamdayne.ecommercelaptop.controller;

import com.lamdayne.ecommercelaptop.dto.request.CreateOrderDTO;
import com.lamdayne.ecommercelaptop.entity.Order;
import com.lamdayne.ecommercelaptop.service.EmailService;
import com.lamdayne.ecommercelaptop.service.OrderService;
import com.lamdayne.ecommercelaptop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.NumberFormat;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class CheckoutController {

    @Value("${spring.mail.username}")
    private String from;

    private final ProductService productService;
    private final OrderService orderService;
    private final EmailService emailService;

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    @PostMapping("/checkout")
    public String checkout(Model model, @RequestParam("productId") String productId) {
        model.addAttribute("product", productService.getProductById(productId));
        return "checkout";
    }

    @PostMapping("/pay")
    public String payOrder(@ModelAttribute CreateOrderDTO createOrderDTO) {
        Order order = orderService.createOrder(createOrderDTO);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String formattedPrice = currencyVN.format(order.getTotalPrice());

        String body = "<h3>Thông tin đơn hàng:</h3>" +
                "Địa chỉ: " + order.getAddress() + "<br/>" +
                "Tổng tiền: <b>" + formattedPrice + "</b>";
        EmailService.Mail mail = EmailService.Mail.builder()
                .from(from)
                .to(createOrderDTO.getEmail())
                .subject("Đặt hàng thành công với mã đơn " + order.getId())
                .body(body)
                .build();
        emailService.send(mail);
        return "redirect:/";
    }
}
