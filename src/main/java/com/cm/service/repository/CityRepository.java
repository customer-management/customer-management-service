package com.cm.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cm.service.models.City;
@Repository
public interface CityRepository extends MongoRepository<City, String> {
	public Optional<City> findByCityCode(String cityCode);

	public Optional<List<City>> findAllByStateCode(String stateCode);
}
