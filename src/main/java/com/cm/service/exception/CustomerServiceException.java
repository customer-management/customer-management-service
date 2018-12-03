package com.cm.service.exception;

import org.springframework.http.HttpStatus;

public class CustomerServiceException extends RuntimeException {
	private static final long serialVersionUID = -1969487623487028697L;
	private String errorCode;
	private HttpStatus httpStatus;

	public CustomerServiceException() {
		super();
	}

	public CustomerServiceException(Throwable cause) {
		super(cause);
	}

	public CustomerServiceException(String message) {
		super(message);
	}
	
	public CustomerServiceException(String message, String errorCode) {
		super(message);
		this.setErrorCode(errorCode);
	}
	
	public CustomerServiceException(String message, String errorCode, HttpStatus status) {
		super(message);
		this.setErrorCode(errorCode);
		this.setHttpStatus(status);
	}

	public CustomerServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
