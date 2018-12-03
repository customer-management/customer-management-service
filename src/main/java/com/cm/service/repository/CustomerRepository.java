package com.cm.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cm.service.models.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>{

}
