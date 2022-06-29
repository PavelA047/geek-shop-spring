package com.example.orderservice.controllers;

import contract.entities.Order;
import contract.utils.CartUserDto;
import contract.utils.ShoppingCart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrderController {

    @PostMapping("/order/make_order")
    Order makeOrder(@RequestParam String userName, @RequestBody ShoppingCart shoppingCart);

    @PostMapping("/order/saveOrder")
    Order saveOrder(@RequestBody Order order);

    @GetMapping("/order/find_by_id")
    Order findById (@RequestParam Long id);
}
