package com.cm.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cm.service.models.hib.Order;
import com.cm.service.repository.OrderRepository;

@Component
public class OrderService {
	@Autowired
	private OrderRepository repository;
	
	public Order addOrder(Order order) {
		return repository.save(order);
	}
	
	public Order updateOrder(Order order) {
		return repository.save(order);
	}

	public Order findOrder(String orderId) {
		return repository.findById(orderId).get();
	}

	public List<Order> findAllOrders() {
		return repository.findAll();
	}
	public  List<Order> findOrdersByPartyId(String partyId) {
		return repository.findAllByPartyId(partyId);
	}
}
