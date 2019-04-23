package com.cm.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cm.service.models.hib.Party;
import com.cm.service.service.PartyService;

@RestController
public class PartyController {

	@Autowired
	private PartyService service;

	@PostMapping(value = "/party", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Party> createParty(@RequestBody Party party) {
		party.setPartyName(party.getPartyName().toUpperCase());
		party.setAddress(party.getAddress().toUpperCase());
		Party savedParty = service.addParty(party);
		return new ResponseEntity<>(savedParty, HttpStatus.CREATED);
	}

	@GetMapping(value = "/party/{partyId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Party> findParty(@PathVariable("partyId") String partyId) {
		Party foundParty = service.findParty(partyId);
		return new ResponseEntity<>(foundParty, HttpStatus.OK);
	}
	
	@GetMapping(value = "/party", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Party>> findAllParties() {
		List<Party> foundParties = service.findAllParties();
		return new ResponseEntity<>(foundParties, HttpStatus.OK);
	}
	
}
