package com.cm.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cm.service.models.Customer;
import com.cm.service.models.CustomerAddress;
import com.cm.service.models.CustomerEmail;
import com.cm.service.models.CustomerMain;
import com.cm.service.models.CustomerPhoneNumber;
import com.cm.service.service.CustomerService;

@RestController
public class CustomerController extends ResponseHelper {
	@Autowired
	private CustomerService service;

	@PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
		CustomerMain addedCustomer = service.addCustomer(customer);
		return sendCreatedResponse(addedCustomer);
	}
	@GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> allCustomers() {
		List<CustomerMain> allCustomers = service.allCustomers();
		
		return sendOkResponse(allCustomers);
	}
	
	// Customer Addresses
	@PostMapping(value = "/customer/{customerId}/address", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> addCustomerAddresses(@RequestBody List<CustomerAddress> customerAddress, 
			@PathVariable("customerId") String customerId) {
		List<CustomerAddress> addedCustomerAddresses = service.addOrUpdateCustomerAddress(customerAddress, customerId);
		return sendCreatedResponse(addedCustomerAddresses);
	}
	@GetMapping(value = "/customer/{customerId}/address", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getCustomerAddresses(@PathVariable("customerId") String customerId) {
		List<CustomerAddress> customerAddresses = service.getCustomerAddress(customerId);
		return sendOkResponse(customerAddresses);
	}
	
	// Customer Emails
	@PostMapping(value = "/customer/{customerId}/emails", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> addCustomerEmails(@RequestBody List<CustomerEmail> customerEmails, 
			@PathVariable("customerId") String customerId) {
		List<CustomerEmail> addedEmails = service.addOrUpdateCustomerEmails(customerEmails, customerId);
		
		return sendCreatedResponse(addedEmails);
	}
	
	@GetMapping(value = "/customer/{customerId}/emails", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getCustomerEmails(@PathVariable("customerId") String customerId) {
		List<CustomerEmail> emails = service.getCustomerEmails(customerId);
		
		return sendOkResponse(emails);
	}
	
	// Customer Phone numbers
		@PostMapping(value = "/customer/{customerId}/phones", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<Object> addCustomerPhoneNumbers(@RequestBody List<CustomerPhoneNumber> customerPhoneNumbers, 
				@PathVariable("customerId") String customerId) {
			List<CustomerPhoneNumber> addedNumbers = service.addOrUpdateCustomerPhoneNumbers(customerPhoneNumbers, customerId);
			
			return sendCreatedResponse(addedNumbers);
		}
		
		@GetMapping(value = "/customer/{customerId}/phones", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<Object> getCustomerPhoneNumbers(@PathVariable("customerId") String customerId) {
			List<CustomerPhoneNumber> customerPhoneNumbers = service.getCustomerPhoneNumbers(customerId);
			return sendOkResponse(customerPhoneNumbers);
		}
	
}
