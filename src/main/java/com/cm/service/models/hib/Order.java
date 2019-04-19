package com.cm.service.models.hib;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

@Entity
@Table(name = "cms_order")
public class Order implements Serializable, CMEntity {
	private static final long serialVersionUID = 2113619543199064119L;
	private static final String ID_PREFIX = "ORD";

	public Order() {
		this.orderStatus = "PENDING";
		this.orderId = generateId(ID_PREFIX);
	}

	@Id
	@Column(name = "order_id", length = 10)
	private String orderId;

	private String orderStatus;
	private float totalOrderAmount;

	@OneToOne
	@JoinColumn(name = "party_id", referencedColumnName = "party_id", nullable = false)
	private Party party;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
	private List<OrderItem> orderItems;

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

//	public String getPartyId() {
//		return partyId;
//	}
//
//	public void setPartyId(String partyId) {
//		this.partyId = partyId;
//	}

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
//		Optional<OrderItem> search = orderItems.stream().filter(item -> item.getStockId().equals(stockId)).findAny();
//		if (search.isPresent()) {
//			OrderItem item = search.get();
//			item.adjustQuantity(quantity);
//		} else {
//			OrderItem item = new OrderItem();
//			item.setStockId(stockId);
//			item.setUnitPrice(unitPrice);
//			item.setQuantity(quantity);
//
//			orderItems.add(item);
//		}
	}

	public void updateTotalOrderAmount() {
		orderItems.forEach(item -> this.totalOrderAmount += item.getOrderTotal());
	}

}
