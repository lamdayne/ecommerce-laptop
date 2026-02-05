package com.lamdayne.ecommercelaptop.controller.admin;

import com.lamdayne.ecommercelaptop.entity.Order;
import com.lamdayne.ecommercelaptop.service.OrderService;
import com.lamdayne.ecommercelaptop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OrderControllerAdmin {

    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/admin/order")
    public String order(Model model, @RequestParam(value = "p", defaultValue = "0") Integer p) {
        Pageable pageable = PageRequest.of(p, 8);
        Page<Order> page = orderService.getOrders(pageable);
        model.addAttribute("page", page);
        return "admin/order/list-order";
    }

    @PostMapping("/admin/order/{orderId}/update-status")
    public String updateStatus(@PathVariable("orderId") String orderId) {
        orderService.updateOrderStatus(orderId, 1);
        return "redirect:/admin/order";
    }

}
