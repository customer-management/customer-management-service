package com.cm.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cm.service.exception.CustomerServiceException;
import com.cm.service.exception.ErrorDetails;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseHelper {
	@ExceptionHandler(value = CustomerServiceException.class)
	public ResponseEntity<Object> handleException(CustomerServiceException ex) {
		return sendErrorResponse(new ErrorDetails(ex.getMessage(), ex.getErrorCode()), ex.getHttpStatus());
	}

}
