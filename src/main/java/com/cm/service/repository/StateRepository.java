package com.cm.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cm.service.models.State;

@Repository
public interface StateRepository extends MongoRepository<State, String> {
	
	public Optional<State> findByStateCode(String stateCode);
	public Optional<List<State>> findAllByCountryCode(String countryCode);
}
