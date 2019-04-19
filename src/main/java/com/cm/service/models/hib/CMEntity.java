package com.cm.service.models.hib;

import java.util.Random;

public interface CMEntity {
	
	default String generateId(String prefix) {
		Random rn = new Random();
		String randomNumber = "00000" + rn.nextLong();
		return prefix.toUpperCase() + randomNumber.substring(randomNumber.length() -5);
	}
}
