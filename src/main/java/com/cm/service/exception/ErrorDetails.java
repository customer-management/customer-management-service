package com.cm.service.exception;

import java.io.Serializable;

public class ErrorDetails implements Serializable {
	private static final long serialVersionUID = -534326746306324702L;

	public static final String NO_DATA_FOUND = "ERRCNDF";
	public static final String SERVICE_ERROR = "ERRSVCF";
	
	public ErrorDetails() {
	}
	
	public ErrorDetails(String message, String errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}

	private String message;
	private String errorCode;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
