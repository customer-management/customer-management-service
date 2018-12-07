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
	@PostMapping(value = "/cities", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> addCities(@RequestBody List<City> cities) {
		validateStateCodes(cities);
		return sendCreatedResponse(cityRepo.insert(cities));
	}

	@GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getCities() {
		return sendOkResponse(cityRepo.findAll());
	}

	@GetMapping(value = "/cities/{cityCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getCity(@PathVariable("cityCode") String cityCode) {
		Optional<City> foundCity = cityRepo.findById(cityCode);
		return sendOkResponse(getBody(foundCity, NO_CITY_FOUND));
	}

	@GetMapping(value = "/cities/states/{stateCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getCitiesByStateCode(@PathVariable("stateCode") String stateCode) {
		Optional<List<City>> foundCities = cityRepo.findAllByStateCode(stateCode);
		return sendOkResponse(getBody(foundCities, NO_CITY_FOUND));
	}

	@PutMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCity(@RequestBody List<City> cities) {
		validateStateCodes(cities);
		return sendOkResponse(cityRepo.saveAll(cities));
	}

	@DeleteMapping(value = "/cities/{cityCode}")
	public ResponseEntity<Object> deleteCity(@PathVariable("cityCode") String cityCode) {
		Optional<City> foundCity = cityRepo.findById(cityCode);
		City city = getBody(foundCity, NO_CITY_FOUND);
		cityRepo.delete(city);
		return sendNoContentResponse();
	}

	// Operations for State
	@PostMapping(value = "/states", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> addStates(@RequestBody List<State> states) {
		validateCountryCodes(states);
		return sendCreatedResponse(stateRepo.insert(states));
	}

	@GetMapping(value = "/states", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getAllStates() {
		return sendOkResponse(stateRepo.findAll());
	}

	@GetMapping(value = "/states/{stateCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getByStateCode(@PathVariable("stateCode") String stateCode) {
		Optional<State> foundState = stateRepo.findById(stateCode);
		State state = getBody(foundState, NO_STATE_FOUND);
		return sendOkResponse(state);
	}

	@GetMapping(value = "/states/country/{countryCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getAllStatesByCountryCode(@PathVariable("countryCode") String countryCode) {
		Optional<List<State>> foundStates = stateRepo.findAllByCountryCode(countryCode);
		List<State> states = getBody(foundStates, NO_STATE_FOUND);
		return sendOkResponse(states);
	}

	@PutMapping(value = "/states", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateState(@RequestBody List<State> states) {
		validateCountryCodes(states);
		return sendOkResponse(stateRepo.saveAll(states));
	}

	@DeleteMapping(value = "/states/{stateCode}")
	public ResponseEntity<Object> deleteState(@PathVariable("stateCode") String stateCode) {
		Optional<State> foundState = stateRepo.findById(stateCode);
		State state = getBody(foundState, NO_STATE_FOUND);
		stateRepo.delete(state);
		return sendNoContentResponse();
	}

	// Operations for Country
	@PostMapping(value = "/countries", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> addCountries(@RequestBody List<Country> countries) {
		return sendCreatedResponse(countryRepo.insert(countries));
	}

	@GetMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getAllCountries() {
		return sendOkResponse(countryRepo.findAll());
	}

	@GetMapping(value = "/countries/{countryCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getByCountryCode(@PathVariable("countryCode") String countryCode) {
		Optional<Country> foundCountry = countryRepo.findById(countryCode);
		Country country = getBody(foundCountry, NO_COUNTRY_FOUND);

		return sendCreatedResponse(country);
	}

	@PutMapping(value = "/countries", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCountries(@RequestBody List<Country> countries) {
		return sendCreatedResponse(countryRepo.saveAll(countries));
	}

	@DeleteMapping(value = "/countries/{countryCode}")
	public ResponseEntity<Object> deleteCountry(@PathVariable("countryCode") String countryCode) {
		Optional<Country> foundCountry = countryRepo.findById(countryCode);
		Country country = getBody(foundCountry, NO_COUNTRY_FOUND);

		countryRepo.delete(country);
		return sendNoContentResponse();
	}

	private void validateStateCodes(List<City> cities) {
		for (City city : cities) {
			String stateCode = city.getStateCode();
			Optional<State> foundState = stateRepo.findById(stateCode);
			getBody(foundState, INVALID_STATE_CODE);
		}
	}

	private void validateCountryCodes(List<State> states) {
		for (State state : states) {
			String countryCode = state.getCountryCode();
			Optional<Country> foundCountry = countryRepo.findById(countryCode);
			getBody(foundCountry, INVALID_COUNTRY_CODE);
		}
	}
}
