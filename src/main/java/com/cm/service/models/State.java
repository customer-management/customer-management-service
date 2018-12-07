package com.cm.service.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class State implements Serializable, Comparable<State> {
	private static final long serialVersionUID = -9488345842L;

	public State() {
	}

	public State(String stateCode) {
		this.stateCode = stateCode;
	}

	@Id
	private String stateCode;
	private String stateName;
	private String countryCode;

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public int compareTo(State o) {
		return this.stateName.compareTo(o.stateName);
	}

}
