package com.example.orderservice.controllers;

import contract.entities.DeliveryAddress;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface DeliveryAddressController {

    @GetMapping("/order/get_user_address")
    List<DeliveryAddress> getUserAddresses(@RequestParam Long id);
}
