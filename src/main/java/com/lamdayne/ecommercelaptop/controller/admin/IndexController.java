package com.lamdayne.ecommercelaptop.controller.admin;

import com.lamdayne.ecommercelaptop.service.BrandService;
import com.lamdayne.ecommercelaptop.service.OrderService;
import com.lamdayne.ecommercelaptop.service.ProductService;
import com.lamdayne.ecommercelaptop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserService userService;
    private final OrderService orderService;
    private final ProductService productService;
    private final BrandService brandService;

    @GetMapping ("/admin/index")
    public String index(Model model) {
        model.addAttribute("product", productService.countProducts());
        model.addAttribute("brand", brandService.countBrands());
        model.addAttribute("order", orderService.countOrders());
        model.addAttribute("user", userService.countUsers());
        return "admin/index";
    }

}
