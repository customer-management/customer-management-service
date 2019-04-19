package com.cm.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.service.models.hib.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
	List<Order> findAllByPartyId(String partyId);
}
