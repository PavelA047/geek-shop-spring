package com.example.orderservice.services;

import com.example.orderservice.repositories.DeliveryAddressRepository;
import contract.entities.DeliveryAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressService {
	@Autowired
	private DeliveryAddressRepository deliveryAddressRepository;
	
	public List<DeliveryAddress> getUserAddresses(Long id) {
		return deliveryAddressRepository.findAllByUserId(id);
//		ArrayList<DeliveryAddress> listOfAddresses = new ArrayList<>();
//		listOfAddresses.add(new DeliveryAddress());
//		return listOfAddresses;
	}
}
