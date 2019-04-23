package com.cm.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cm.service.models.hib.Stock;
import com.cm.service.repository.StockRepository;

@Component
public class StockService {
	@Autowired
	private StockRepository repository;
	@Autowired
	private ChangeLogService changeLogService;

	public Stock addStock(Stock stock) {
		Stock existing = repository.getStockByStockDescription(stock.getStockDescription());
		if (existing != null) {
			int updatedAvailavleStock = existing.getAvailableStocks() + stock.getAvailableStocks();
			float prevDiscount = existing.getDiscount() * existing.getAvailableStocks();
			float currentDiscount = stock.getDiscount() * stock.getAvailableStocks();

			float unitPrice = ((existing.getUnitPrice() * existing.getAvailableStocks())
					+ (stock.getUnitPrice() * stock.getAvailableStocks())) / updatedAvailavleStock;

			existing.setAvailableStocks(updatedAvailavleStock);
			existing.setDiscount((prevDiscount + currentDiscount) / updatedAvailavleStock);

			existing.setUnitPrice(unitPrice);

			Stock updated = repository.save(existing);
			changeLogService.insertChangeLog("STOCK", updated, "UP");
			return updated;
		}
		Stock inserted = repository.save(stock);
		changeLogService.insertChangeLog("STOCK", inserted, "IN");
		return inserted;
	}

	public Stock updateStock(Stock stock) {
		Stock updated = repository.save(stock);
		changeLogService.insertChangeLog("STOCK", updated, "UP");
		return updated;
	}

	public Stock findStock(String stockId) {
		Optional<Stock> found = repository.findById(stockId);
		return found.isPresent() ? found.get() : null;
	}

	public List<Stock> findAllStocks() {
		return repository.findAll();
	}
}
