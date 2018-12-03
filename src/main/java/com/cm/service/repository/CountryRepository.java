package com.cm.service.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cm.service.models.Country;

@Repository
public interface CountryRepository extends MongoRepository<Country, String> {
	public Optional<Country> findByCountryCode(String countryCode);
}
