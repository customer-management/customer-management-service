package com.cm.service.models.hib;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cms_stock")
public class Stock implements Serializable, CMEntity {
	private static final long serialVersionUID = 5925707490736701166L;
	private static final String ID_PREFIX = "STK";

	public Stock() {
		this.stockId = generateId(ID_PREFIX);
	}

	@Id
	@Column(name = "stock_id", length = 10)
	private String stockId;
	private String stockDescription;
	private float unitPrice;
	private float discount;
	private int availableStocks;

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getStockDescription() {
		return stockDescription;
	}

	public void setStockDescription(String stockDescription) {
		this.stockDescription = stockDescription;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public int getAvailableStocks() {
		return availableStocks;
	}

	public void setAvailableStocks(int availableStocks) {
		this.availableStocks = availableStocks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stockId == null) ? 0 : stockId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (stockId == null) {
			if (other.stockId != null)
				return false;
		} else if (!stockId.equals(other.stockId))
			return false;
		return true;
	}
	
	
}
