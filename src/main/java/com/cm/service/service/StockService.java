package com.cm.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cm.service.models.hib.Stock;
import com.cm.service.repository.StockRepository;

@Component
public class StockService {
	@Autowired
	private StockRepository repository;

	public Stock addStock(Stock stock) {
		return repository.save(stock);
	}

	public Stock updateStock(Stock stock) {
		return repository.save(stock);
	}

	public Stock findStock(String stockId) {
		return repository.findById(stockId).get();
	}

	public List<Stock> findAllStocks() {
		return repository.findAll();
	}
}
