package com.cm.service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cm.service.models.City;
import com.cm.service.models.Country;
import com.cm.service.models.State;
import com.cm.service.repository.CityRepository;
import com.cm.service.repository.CountryRepository;
import com.cm.service.repository.StateRepository;

@RestController
public class MetadataController extends ResponseHelper {
	@Autowired
	private CityRepository cityRepo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CountryRepository countryRepo;

	// Operations for City
	@PostMapping(value = "/city", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> addCity(@RequestBody City city) {
		return sendCreatedResponse(cityRepo.insert(city));
	}
	@GetMapping(value = "/city", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getCities() {
		return sendOkResponse(cityRepo.findAll());
	}
	@GetMapping(value = "/city/{cityCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getCity(@PathVariable("cityCode") String cityCode) {
		Optional<City> foundCity = cityRepo.findByCityCode(cityCode);
		return sendOkResponse(getBody(foundCity, NO_CITY_FOUND));
	}
	@GetMapping(value = "/city/state/{stateCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getCitiesByStateCode(@PathVariable("stateCode") String stateCode) {
		Optional<List<City>> foundCities = cityRepo.findAllByStateCode(stateCode);
		return sendOkResponse(getBody(foundCities, NO_CITY_FOUND));
	}
	@PutMapping(value = "/city", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCity(@RequestBody City city) {
		return sendOkResponse(cityRepo.save(city));
	}
	@DeleteMapping(value = "/city/{cityCode}")
	public ResponseEntity<Object> deleteCity(@PathVariable("cityCode") String cityCode) {
		Optional<City> foundCity = cityRepo.findByCityCode(cityCode);
		City city = getBody(foundCity, NO_CITY_FOUND);
		cityRepo.delete(city);
		return sendNoContentResponse();
	}
	
	// Operations for State
	@PostMapping(value = "/state", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> addState(@RequestBody State state) {
		return sendCreatedResponse(stateRepo.insert(state));
	}
	@GetMapping(value = "/state", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getAllStates() {
		return sendOkResponse(stateRepo.findAll());
	}
	@GetMapping(value = "/state/{stateCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getByStateCode(@PathVariable("stateCode") String stateCode) {
		Optional<State> foundState = stateRepo.findByStateCode(stateCode);
		State state = getBody(foundState, NO_STATE_FOUND);
		return sendOkResponse(state);
	}
	@GetMapping(value = "/state/country/{countryCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getAllStatesByCountryCode(@PathVariable("countryCode") String countryCode) {
		Optional<List<State>> foundStates = stateRepo.findAllByCountryCode(countryCode);
		List<State> states = getBody(foundStates, NO_STATE_FOUND);
		return sendOkResponse(states);
	}
	@PutMapping(value = "/state", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateState(@RequestBody State state) {
		return sendOkResponse(stateRepo.save(state));
	}
	@DeleteMapping(value = "/state/{stateCode}")
	public ResponseEntity<Object> deleteState(@PathVariable("stateCode") String stateCode) {
		Optional<State> foundState = stateRepo.findByStateCode(stateCode);
		State state = getBody(foundState, NO_STATE_FOUND);
		stateRepo.delete(state);
		return sendNoContentResponse();
	}
	
	
	// Operations for Country
	@PostMapping(value = "/country", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> addCountry(@RequestBody Country country) {
		return sendCreatedResponse(countryRepo.insert(country));
	}
	@PostMapping(value = "/country/multi", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> addCountry(@RequestBody List<Country> countries) {
		return sendCreatedResponse(countryRepo.saveAll(countries));
	}
	@GetMapping(value = "/country", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getAllCountries() {
		return sendOkResponse(countryRepo.findAll());
	}
	@GetMapping(value = "/country/{countryCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getByCountryCode(@PathVariable("countryCode") String countryCode) {
		Optional<Country> foundCountry = countryRepo.findByCountryCode(countryCode);
		Country country = getBody(foundCountry, NO_COUNTRY_FOUND);
		
		return sendCreatedResponse(country);
	}
	@DeleteMapping(value = "/country/{countryCode}")
	public ResponseEntity<Object> deleteCountry(@PathVariable("countryCode") String countryCode) {
		Optional<Country> foundCountry = countryRepo.findByCountryCode(countryCode);
		Country country = getBody(foundCountry, NO_COUNTRY_FOUND);
		
		countryRepo.delete(country);
		return sendNoContentResponse();
	}
}
