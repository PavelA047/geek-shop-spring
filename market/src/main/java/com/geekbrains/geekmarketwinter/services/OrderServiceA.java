package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.repositories.OrderRepository;
import contract.entities.Order;
import contract.entities.OrderItem;
import contract.entities.User;
import contract.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceA {
	
	private OrderRepository orderRepository;
	
	private OrderStatusServiceA orderStatusServiceA;
	
	@Autowired
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@Autowired
	public void setOrderStatusService(OrderStatusServiceA orderStatusServiceA) {
		this.orderStatusServiceA = orderStatusServiceA;
	}
	
	@Transactional
	public Order makeOrder(ShoppingCart cart, User user) {
		Order order = new Order();
		order.setId(0L);
		order.setUser(user);
		order.setStatus(orderStatusServiceA.getStatusById(1L));
		order.setPrice(cart.getTotalCost());
		order.setOrderItems(new ArrayList<>(cart.getItems()));
		for (OrderItem o : cart.getItems()) {
			o.setOrder(order);
		}
		return order;
	}
	
	public List<Order> getAllOrders() {
		return (List<Order>) orderRepository.findAll();
	}
	
	public Order findById(Long id) {
		return orderRepository.findById(id).get();
	}
	
	public Order saveOrder(Order order) {
		Order orderOut = orderRepository.save(order);
		orderOut.setConfirmed(true);
		return orderOut;
	}
	
	public Order changeOrderStatus(Order order, Long statusId) {
		order.setStatus(orderStatusServiceA.getStatusById(statusId));
		return saveOrder(order);
	}
}
