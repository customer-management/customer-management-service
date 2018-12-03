package com.cm.service.models;

import java.io.Serializable;

public class State implements Serializable {
	private static final long serialVersionUID = -9488345842L;
	
	public State() {
	}
	
	public State(String stateCode) {
		this.stateCode = stateCode;
	}

	private String id;
	private String stateCode;
	private String stateName;
	private String countryCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


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

}
