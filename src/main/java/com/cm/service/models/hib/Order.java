package com.cm.service.models.hib;

import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

@Entity
@Table(name = "cms_order")
public class Order {
	public Order() {
		this.orderStatus = "PENDING";
	}
	@Id
	@Column(name = "order_id")
	private String orderId;

	private String partyId;
	private String orderStatus;
	private float totalOrderAmount;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private List<OrderItem> orderItems;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		if (StringUtils.isEmpty(orderStatus)) {
			this.orderStatus = "PENDING";
		} else {
			this.orderStatus = orderStatus;
		}
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public float getTotalOrderAmount() {
		return totalOrderAmount;
	}

	public void setTotalOrderAmount(float totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}

	public void addOrderItem(String stockId, float unitPrice, int quantity) {
		Optional<OrderItem> search = orderItems.stream().filter(item -> item.getStockId().equals(stockId)).findAny();
		if (search.isPresent()) {
			OrderItem item = search.get();
			item.adjustQuantity(quantity);
		} else {
			OrderItem item = new OrderItem();
			item.setStockId(stockId);
			item.setUnitPrice(unitPrice);
			item.setQuantity(quantity);

			orderItems.add(item);
		}
	}
	
	public void updateTotalOrderAmount() {
		orderItems.forEach(item -> this.totalOrderAmount += item.getOrderTotal());
	}

}
