package com.cm.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cm.service.models.hib.Party;
import com.cm.service.repository.PartyRepository;

@Component
public class PartyService {
	@Autowired
	private PartyRepository repository;
	
	public Party addParty(Party party) {
		return repository.save(party);
	}
	
	public Party updateParty(Party party) {
		return repository.save(party);
	}

	public Party findParty(String partyId) {
		return repository.findById(partyId).get();
	}

	public List<Party> findAllParties() {
		return repository.findAll();
	}
}
