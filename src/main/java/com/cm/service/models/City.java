package com.cm.service.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class City implements Serializable, Comparable<City> {
	private static final long serialVersionUID = -57683930201L;

	public City() {
	}

	public City(String cityCode) {
		this.cityCode = cityCode;
	}

	@Id
	private String cityCode;
	private String cityName;
	private String stateCode;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@Override
	public int compareTo(City o) {
		return this.cityName.compareTo(o.cityName);
	}
}
