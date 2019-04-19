package com.cm.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.service.models.hib.Order;
import com.cm.service.models.hib.Party;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
	
	default Order findPendingOrderForParty(Party party) {
		return findOrderByPartyAndOrderStatus(party, "PENDING");
	}
	
	Order findOrderByPartyAndOrderStatus(Party party, String orderStatus);
}
