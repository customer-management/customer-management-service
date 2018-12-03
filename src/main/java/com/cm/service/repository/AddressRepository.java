package com.cm.service.repository;

import org.springframework.stereotype.Repository;

import com.cm.service.models.CustomerAddress;
@Repository
public interface AddressRepository extends CustomerModelRepository<CustomerAddress, String> {

}
