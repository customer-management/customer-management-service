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

import com.cm.service.models.hib.Stock;
import com.cm.service.service.StockService;

@RestController
public class StockController {

	@Autowired
	private StockService service;

	@PostMapping(value = "/stock", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
		Stock savedStock = service.addStock(stock);
		return new ResponseEntity<>(savedStock, HttpStatus.CREATED);
	}

	@GetMapping(value = "/stock/{stockId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Stock> findStock(@PathVariable("stockId") String stockId) {
		Stock foundStock = service.findStock(stockId);
		return new ResponseEntity<>(foundStock, HttpStatus.OK);
	}
	
	@GetMapping(value = "/stock", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Stock>> findAllParties() {
		List<Stock> foundStocks = service.findAllStocks();
		return new ResponseEntity<>(foundStocks, HttpStatus.OK);
	}
	
}
