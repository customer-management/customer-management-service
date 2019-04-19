package com.cm.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cm.service.models.hib.Order;
import com.cm.service.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	private OrderService service;

	@PostMapping(value = "/order", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		order.updateTotalOrderAmount();
		Order savedOrder = service.addOrder(order);
		return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
	}

	@GetMapping(value = "/order/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Order> findOrder(@PathVariable("orderId") String orderId) {
		Order foundOrder = service.findOrder(orderId);
		return new ResponseEntity<>(foundOrder, HttpStatus.OK);
	}
	
	@GetMapping(value = "/order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Order>> findAllOrders() {
		List<Order> foundOrders = service.findAllOrders();
		return new ResponseEntity<>(foundOrders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/order/party/{partyId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Order>> findOrdersByPartyId(@PathVariable("partyId") String partyId) {
		List<Order> foundOrders = service.findOrdersByPartyId(partyId);
		return new ResponseEntity<>(foundOrders, HttpStatus.OK);
	}
}
