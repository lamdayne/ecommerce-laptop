package com.lamdayne.ecommercelaptop.controller;

import com.lamdayne.ecommercelaptop.dto.request.CreateOrderDTO;
import com.lamdayne.ecommercelaptop.service.OrderService;
import com.lamdayne.ecommercelaptop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CheckoutController {

    private final ProductService productService;
    private final OrderService orderService;

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
        orderService.createOrder(createOrderDTO);
        return "redirect:/";
    }
}
