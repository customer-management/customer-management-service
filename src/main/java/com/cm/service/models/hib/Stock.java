package com.cm.service.models.hib;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cms_stock")
public class Stock implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String stockRecordId;
	
	private String stockId;
	
	private int unitQuantity;

	public String getStockRecordId() {
		return stockRecordId;
	}

	public void setStockRecordId(String stockRecordId) {
		this.stockRecordId = stockRecordId;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public int getUnitQuantity() {
		return unitQuantity;
	}

	public void setUnitQuantity(int unitQuantity) {
		this.unitQuantity = unitQuantity;
	}
}