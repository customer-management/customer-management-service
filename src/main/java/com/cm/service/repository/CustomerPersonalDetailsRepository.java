package com.cm.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cm.service.models.CustomerPersonalDetails;

@Repository
public interface CustomerPersonalDetailsRepository extends MongoRepository<CustomerPersonalDetails, String>{

}
