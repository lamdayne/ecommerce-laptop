package com.lamdayne.ecommercelaptop.controller;

import com.lamdayne.ecommercelaptop.constant.SessionConstant;
import com.lamdayne.ecommercelaptop.dto.request.CreateOrderDTO;
import com.lamdayne.ecommercelaptop.dto.response.ProductResponse;
import com.lamdayne.ecommercelaptop.entity.Order;
import com.lamdayne.ecommercelaptop.entity.Product;
import com.lamdayne.ecommercelaptop.entity.User;
import com.lamdayne.ecommercelaptop.service.EmailService;
import com.lamdayne.ecommercelaptop.service.OrderService;
import com.lamdayne.ecommercelaptop.service.ProductService;
import com.lamdayne.ecommercelaptop.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class CheckoutController {

    private final SessionUtil sessionUtil;
    @Value("${spring.mail.username}")
    private String from;

    private final ProductService productService;
    private final OrderService orderService;
    private final EmailService emailService;

    @GetMapping("/checkout")
    public String checkout() {
        return "redirect:/";
    }

    @PostMapping("/checkout")
    public String checkout(Model model,
                           @RequestParam("productIds") List<String> productIds,
                           @RequestParam("quantities") List<Integer> quantities,
                           @RequestParam("fromCart") boolean fromCart
    ) {
        List<ProductResponse> products = new ArrayList<>();
        double totalPrice = 0;
        User user = (User) sessionUtil.get(SessionConstant.SESSION_USER);

        for (int i = 0; i < productIds.size(); i++) {
            ProductResponse product = productService.getProductById(productIds.get(i));
            products.add(product);
            totalPrice += (product.getSalePrice() * quantities.get(i));
        }
        model.addAttribute("products", products);
        model.addAttribute("quantities", quantities);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("fromCart", fromCart);
        return "checkout";
    }

    @PostMapping("/pay")
    public String payOrder(@ModelAttribute CreateOrderDTO createOrderDTO) {
        Order order = orderService.createOrder(createOrderDTO);
        return "redirect:/";
    }
}
