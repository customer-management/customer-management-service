package com.cm.service.models.hib;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cms_order_item")
public class OrderItem {
	@Id
	@Column(name="order_item_id")
	private String orderItemId;
	private String stockId;
	private int quantity;
	private float unitPrice;
	private float orderTotal;

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		updateOrderTotal();
	}

	public float getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public void adjustQuantity(int quantity) {
		this.quantity += quantity;
		updateOrderTotal();
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
		updateOrderTotal();
	}

	private void updateOrderTotal() {
		this.orderTotal = this.unitPrice * this.quantity;
	}

}
