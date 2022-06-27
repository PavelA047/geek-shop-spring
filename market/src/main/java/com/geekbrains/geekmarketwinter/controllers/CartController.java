package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.services.ProductService;
import contract.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.geekbrains.geekmarketwinter.services.ShoppingCartService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
    private ShoppingCartService shoppingCartService;
    private ProductService productService;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String cartPage(Model model, HttpSession httpSession) {
        ShoppingCart cart = shoppingCartService.getCurrentCart(httpSession);
        model.addAttribute("cart", cart);
        return "cart-page";
    }

    @DeleteMapping("/{title}")
    public String deleteFromCart(@PathVariable("title") String title, HttpSession httpSession) {
        shoppingCartService.removeFromCart(httpSession, productService.getProductByTitle(title));
        return "redirect:/cart";
    }
}
