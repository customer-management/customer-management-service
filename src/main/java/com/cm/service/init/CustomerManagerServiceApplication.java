package com.cm.service.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.cm.service")
public class CustomerManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerManagerServiceApplication.class, args);
	}
}
