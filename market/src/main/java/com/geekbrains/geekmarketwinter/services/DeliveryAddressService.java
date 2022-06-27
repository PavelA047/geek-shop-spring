package com.geekbrains.geekmarketwinter.services;

import contract.entities.DeliveryAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "order-service-client", contextId = "order-service-delivery")
public interface DeliveryAddressService {

    @GetMapping("/order/get_user_address")
    List<DeliveryAddress> getUserAddresses(@RequestParam Long id);
}
