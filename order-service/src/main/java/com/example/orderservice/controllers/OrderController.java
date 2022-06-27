package com.example.orderservice.controllers;

import contract.entities.Order;
import contract.utils.CartUserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrderController {

    @PostMapping("/order/make_order")
    Order makeOrder(@RequestBody CartUserDto cartUserDto);

    @PostMapping("/order/saveOrder")
    Order saveOrder(@RequestBody Order order);

    @GetMapping("/order/find_by_id")
    Order findById (@RequestParam Long id);
}
