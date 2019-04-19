package com.cm.service.exception;

public class InsufficientStockException extends RuntimeException {
	private static final long serialVersionUID = -453438223805774043L;
	
	private InsufficientStockException(String message) {
		super(message);
	}
	
	public static void throwNew(String stockName, int available, int requested) {
		String message = "Insufficient stock for [" + stockName + "]. Available [" + available + "], requested [" + requested +"].";
		throw new InsufficientStockException(message);
	}
}
