package com.cm.service.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Country implements Serializable, Comparable<Country> {
	private static final long serialVersionUID = -15563754842L;

	public Country() {
	}

	public Country(String countryCode) {
		this.countryCode = countryCode;
	}

	// private String id;
	@Id
	private String countryCode;
	private String countryName;

	/*
	 * public String getId() { return id; }
	 *
	 * public void setId(String id) { this.id = id; }
	 */

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

	@Override
	public int compareTo(Country o) {
		return countryName.compareTo(o.countryName);
	}

}
