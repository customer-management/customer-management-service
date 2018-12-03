package com.cm.service.models;

import java.io.Serializable;

public class Country implements Serializable {
	private static final long serialVersionUID = -15563754842L;
	
	
	public Country() {
	}
	public Country(String countryCode) {
		this.countryCode = countryCode;
	}

	private String id;
	private String countryCode;
	private String countryName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
