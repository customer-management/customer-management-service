package com.cm.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.service.models.hib.Party;

@Repository
public interface PartyRepository extends JpaRepository<Party, String> {

}
