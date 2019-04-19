package com.cm.service.exception;

public class InvalidOrderException extends RuntimeException {
	private static final long serialVersionUID = -453438223805774043L;
	
	private InvalidOrderException(String message) {
		super(message);
	}
	
	public static void throwNew() {
		String message = "Your order contains no item. Please add at least one item to place an order.";
		throw new InvalidOrderException(message);
	}
	public static void throwNew(String message) {
		throw new InvalidOrderException(message);
	}
}
