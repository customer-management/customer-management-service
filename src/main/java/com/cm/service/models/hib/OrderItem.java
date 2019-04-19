package com.cm.service.models.hib;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cms_order_item")
public class OrderItem implements Serializable, CMEntity {
	private static final long serialVersionUID = 3896851921431344675L;
	private static final String ID_PREFIX = "ORI";

	public OrderItem() {
		this.orderItemId = generateId(ID_PREFIX);
	}

	@Id
	@Column(name = "order_item_id", length = 10)
	private String orderItemId;
	private int quantity;
	private float orderTotal;

	@OneToOne
	@JoinColumn(name = "stock_id", referencedColumnName = "stock_id", nullable = false)
	private Stock stock;

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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


}
