package com.cm.service.models.hib;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cms_party")
public class Party implements Serializable, CMEntity {
	private static final long serialVersionUID = -3620562262886952263L;

	private static final String ID_PREFIX = "PAR";
	
	public Party() {
		this.partyId = generateId(ID_PREFIX);
	}
	
	@Id
	@Column(name = "party_id", length = 10)
	private String partyId;

	@Column(name = "party_name")
	private String partyName;

	@Column(name = "party_email")
	private String email;

	@Column(name = "party_phone")
	private String phone;

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
