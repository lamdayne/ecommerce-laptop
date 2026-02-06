package com.lamdayne.ecommercelaptop.controller;

import com.lamdayne.ecommercelaptop.constant.SessionConstant;
import com.lamdayne.ecommercelaptop.entity.Cart;
import com.lamdayne.ecommercelaptop.entity.User;
import com.lamdayne.ecommercelaptop.service.CartService;
import com.lamdayne.ecommercelaptop.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CartUserController {

    private final CartService cartService;
    private final SessionUtil sessionUtil;

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("title", "Giỏ hàng test");
        model.addAttribute("message", "Trang giỏ hàng đang hoạt động!");
        User user = (User) sessionUtil.get(SessionConstant.SESSION_USER);
        model.addAttribute("cartItems", cartService.findByUserId(user));
        return "cart";
    }

    @PostMapping("/add-to-cart")
    public String cartPost(Model model, @RequestParam("productId") String productId) {
        cartService.addToCart(productId);
        return "redirect:/detail/" + productId;
    }

    @PostMapping("/cart/remove/{productId}")
    public String cartRemoveFromCart(Model model,
                                     @PathVariable("productId") String productId,
                                     @RequestParam("cartId") String cartId,
                                     RedirectAttributes re
    ) {
        cartService.deleteProductFromCart(cartId, productId);
        User user = (User) sessionUtil.get(SessionConstant.SESSION_USER);
        user.getCarts().removeIf(cart -> cart.getId().equals(cartId));
        re.addFlashAttribute("deleteSuccess", "Xóa sản phẩm thành công");
        return "redirect:/cart";
    }

}
