package com.cm.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cm.service.exception.InsufficientStockException;
import com.cm.service.exception.InvalidOrderException;
import com.cm.service.models.hib.Order;
import com.cm.service.models.hib.OrderItem;
import com.cm.service.models.hib.Stock;
import com.cm.service.repository.OrderRepository;

@Component
public class OrderService {
	@Autowired
	private OrderRepository repository;

	@Autowired
	private StockService stockService;

	public Order addOrder(Order order) {
		verifyOrderIntegrity(order);
		Order toSave = checkAndMergePreviousOrder(order);
		updateOrderValue(toSave);
		Order saved = repository.save(toSave);
		updateStock(order);
		return saved;
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

	public List<Order> findOrdersByPartyId(String partyId) {
		// return repository.findAllByPartyId(partyId);
		return null;
	}

	private void verifyOrderIntegrity(Order order) {
		if (CollectionUtils.isEmpty(order.getOrderItems())) {
			InvalidOrderException.throwNew();
		}
		order.getOrderItems().forEach(item -> {
			Stock stock = item.getStock();
			Stock foundStock = stockService.findStock(stock.getStockId());
			if (foundStock == null) {
				InvalidOrderException.throwNew("Invalid stock [" + stock.getStockId() + "].");
			}
			if (item.getQuantity() > (foundStock.getAvailableStocks())) {
				InsufficientStockException.throwNew(foundStock.getStockDescription(), foundStock.getAvailableStocks(),
						item.getQuantity());
			}
			item.setStock(foundStock);
		});
	}

	private void updateOrderValue(Order order) {
		order.setTotalOrderAmount(0);
		order.getOrderItems().forEach(item -> {
			Stock stock = item.getStock().getUnitPrice() == 0 ?  stockService.findStock(item.getStock().getStockId()) : item.getStock();
			float totalItemPrice = item.getQuantity() * stock.getUnitPrice();

			item.setOrderTotal(totalItemPrice - (totalItemPrice * item.getStock().getDiscount() / 100));
			order.setTotalOrderAmount(order.getTotalOrderAmount() + item.getOrderTotal());
		});
	}

	private void updateStock(Order order) {
		order.getOrderItems().forEach(item -> {
			Stock found = stockService.findStock(item.getStock().getStockId());
			found.setAvailableStocks(found.getAvailableStocks() - item.getQuantity());
			item.setStock(stockService.updateStock(found));
		});
	}
	private Order checkAndMergePreviousOrder(Order currentOrder) {
		Order previousOrder = repository.findPendingOrderForParty(currentOrder.getParty());
		if(previousOrder == null) {
			return currentOrder;
		}
		List<OrderItem> previousItems = previousOrder.getOrderItems();
		List<OrderItem> currentItems = new ArrayList<>(currentOrder.getOrderItems());
		previousItems.forEach(previousItem -> {
			Optional<OrderItem> search = currentItems.stream().filter(currentItem -> currentItem.getStock().equals(previousItem.getStock())).findAny();
			if(search.isPresent()) {
				previousItem.setQuantity(previousItem.getQuantity() + search.get().getQuantity());
				currentItems.remove(search.get());
			}
		});
		previousItems.addAll(currentItems);
		return previousOrder;
	}
}
