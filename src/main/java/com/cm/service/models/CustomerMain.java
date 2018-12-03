package com.cm.service.models;

import java.io.Serializable;
import java.util.List;

public class CustomerMain implements Serializable {
	private static final long serialVersionUID = -9488489898982L;

	private CustomerPersonalDetails personalDetails;
	private List<CustomerAddressModel> addresses;
	private List<CustomerEmail> emails;
	private List<CustomerPhoneNumber> phoneNumbers;
	public CustomerPersonalDetails getPersonalDetails() {
		return personalDetails;
	}
	public void setPersonalDetails(CustomerPersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}
	public List<CustomerAddressModel> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<CustomerAddressModel> addresses) {
		this.addresses = addresses;
	}
	public List<CustomerEmail> getEmails() {
		return emails;
	}
	public void setEmails(List<CustomerEmail> emails) {
		this.emails = emails;
	}
	public List<CustomerPhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<CustomerPhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
}
