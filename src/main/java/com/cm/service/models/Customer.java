package com.cm.service.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer implements Serializable {
	private static final long serialVersionUID = -9488548842L;
	@Id
	private String id;
	private String firstName;
	private String middleName;
	private String lastname;
	private String title;
	private String sex;
	private int age;
	private String fatherName;
	private String maritalStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public static Customer fromPersonalDetails(CustomerPersonalDetails personalDetails) {
		Customer customer = new Customer();
		customer.setId(personalDetails.getFirstName());
		customer.setAge(personalDetails.getAge());
		customer.setFatherName(personalDetails.getFatherName());
		customer.setTitle(personalDetails.getTitle());
		customer.setFirstName(personalDetails.getFirstName());
		customer.setMiddleName(personalDetails.getMiddleName());
		customer.setLastname(personalDetails.getLastname());
		customer.setMaritalStatus(personalDetails.getMaritalStatus());
		customer.setSex(personalDetails.getSex());

		return customer;
	}

	public CustomerPersonalDetails toPersonalDetails() {
		CustomerPersonalDetails customerPersonalDetails = new CustomerPersonalDetails();
		customerPersonalDetails.setId(this.getId());
		customerPersonalDetails.setAge(this.getAge());
		customerPersonalDetails.setFatherName(this.getFatherName());
		customerPersonalDetails.setTitle(this.getTitle());
		customerPersonalDetails.setFirstName(this.getFirstName());
		customerPersonalDetails.setMiddleName(this.getMiddleName());
		customerPersonalDetails.setLastname(this.getLastname());
		customerPersonalDetails.setMaritalStatus(this.getMaritalStatus());
		customerPersonalDetails.setSex(this.getSex());

		return customerPersonalDetails;
	}
}
