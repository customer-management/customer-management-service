package com.cm.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cm.service.models.CustomerModel;

@Repository
public interface CustomerModelRepository<T extends CustomerModel, ID> extends MongoRepository<T, ID> {
	Optional<List<T>> findAllByCustomerId(String customerId);
}
