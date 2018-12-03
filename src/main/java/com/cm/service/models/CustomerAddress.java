package com.cm.service.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CustomerAddress implements Serializable, CustomerModel {
	private static final long serialVersionUID = -9488293042L;
	@Id
	private String id;
	private String customerId;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String pin;
	private String city;

	private boolean isPrimary;
	private String addressType;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public static CustomerAddress fromCustomerAddressModel(CustomerAddressModel customerAddressModel) {
		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setAddressLine1(customerAddressModel.getAddressLine1());
		customerAddress.setAddressLine2(customerAddressModel.getAddressLine2());
		customerAddress.setAddressLine3(customerAddressModel.getAddressLine3());
		customerAddress.setAddressType(customerAddressModel.getAddressType());
		customerAddress.setCity(customerAddressModel.getCity().getCityCode());
		customerAddress.setCustomerId(customerAddressModel.getCustomerId());
		customerAddress.setId(customerAddressModel.getId());
		customerAddress.setPin(customerAddressModel.getPin());
		customerAddress.setPrimary(customerAddressModel.isPrimary());
		
		return customerAddress;
	}
	public CustomerAddressModel toCustomerAddressModel() {
		CustomerAddressModel customerAddressModel = new CustomerAddressModel();
		customerAddressModel.setAddressLine1(this.getAddressLine1());
		customerAddressModel.setAddressLine2(this.getAddressLine2());
		customerAddressModel.setAddressLine3(this.getAddressLine3());
		customerAddressModel.setAddressType(this.getAddressType());
		customerAddressModel.setCity(new City(this.getCity()));
		customerAddressModel.setCustomerId(this.getCustomerId());
		customerAddressModel.setId(this.getId());
		customerAddressModel.setPin(this.getPin());
		customerAddressModel.setPrimary(customerAddressModel.isPrimary());
		
		return customerAddressModel;
	}
}
