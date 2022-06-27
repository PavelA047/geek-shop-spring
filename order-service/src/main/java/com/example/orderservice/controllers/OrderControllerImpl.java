package com.example.orderservice.controllers;

import com.example.orderservice.services.OrderService;
import contract.entities.Order;
import contract.utils.CartUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerImpl implements OrderController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Order makeOrder(CartUserDto cartUserDto) {
        return orderService.makeOrder(cartUserDto.getShoppingCart(), cartUserDto.getUser());
    }

    @Override
    public Order saveOrder(Order order) {
        return orderService.saveOrder(order);
    }

    @Override
    public Order findById(Long id) {
        return orderService.findById(id);
    }
}
