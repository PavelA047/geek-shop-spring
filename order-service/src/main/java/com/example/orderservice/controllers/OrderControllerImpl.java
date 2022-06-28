package com.example.orderservice.controllers;

import com.example.orderservice.services.OrderService;
import com.example.orderservice.services.RegistrationService;
import contract.entities.Order;
import contract.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerImpl implements OrderController {

    private OrderService orderService;

    private RegistrationService registrationService;

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Order makeOrder(String userName, ShoppingCart shoppingCart) {
        return orderService.makeOrder(shoppingCart, registrationService.getUser(userName));
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
