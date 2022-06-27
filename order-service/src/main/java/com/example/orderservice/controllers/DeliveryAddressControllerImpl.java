package com.example.orderservice.controllers;

import com.example.orderservice.repositories.DeliveryAddressRepository;
import com.example.orderservice.services.DeliveryAddressService;
import contract.entities.DeliveryAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeliveryAddressControllerImpl implements DeliveryAddressController {

    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    private DeliveryAddressService deliveryAddressService;

    @Override
    public List<DeliveryAddress> getUserAddresses(Long id) {
        return deliveryAddressService.getUserAddresses(id);
//		ArrayList<DeliveryAddress> listOfAddresses = new ArrayList<>();
//		listOfAddresses.add(new DeliveryAddress());
//		return listOfAddresses;
    }
}
