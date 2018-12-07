package com.cm.service.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.cm.service.exception.CustomerServiceException;
import com.cm.service.exception.ErrorDetails;

@Component
public class ResponseHelper {
	public static final String NO_CITY_FOUND = "No city was found.";
	public static final String NO_STATE_FOUND = "No state was found.";
	public static final String NO_COUNTRY_FOUND = "No country was found.";
	public static final String INVALID_COUNTRY_CODE = "Invalid Country code was entered or this Country code does not exist in our system. Please enter a valid Country code.";
	public static final String INVALID_STATE_CODE = "Invalid State code was entered or this State code does not exist in our system. Please enter a valid State code.";
	public static final String INVALID_CITY_CODE = "Invalid City code was entered or this City code does not exist in our system. Please enter a valid City code.";
	public static final String INVALID_CUSTOMER_ID = "This customer id does not associated to valid customer.";

	public <T> ResponseEntity<T> sendCreatedResponse(T body) {
		sort(body);
		return new ResponseEntity<>(body, HttpStatus.CREATED);
	}
	public <T> ResponseEntity<T> sendOkResponse(T body) {
		sort(body);
		return new ResponseEntity<>(body, HttpStatus.OK);
	}

	public <T> ResponseEntity<T> sendNoContentResponse() {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	public <T> ResponseEntity<T> sendErrorResponse(T body, HttpStatus httpStatus) {
		return new ResponseEntity<>(body, httpStatus);
	}
	public <T> T getBody(Optional<T> obj, String message) {
		if(!obj.isPresent()) {
			throw new CustomerServiceException(message, ErrorDetails.NO_DATA_FOUND, HttpStatus.NOT_FOUND);
		}
		return obj.get();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void sort(Object body) {
		if (body instanceof List) {
			List list = (List) body;
			if (list != null && (list.get(0) instanceof Comparable)) {
				Collections.sort(list);
			}
		}
	}
}
