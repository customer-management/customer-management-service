package com.cm.service.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.cm.service")
@EnableJpaRepositories(basePackages = "com.cm.service.repository")
@EntityScan(basePackages = "com.cm.service.models.hib")
public class CustomerManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerManagerServiceApplication.class, args);
	}
}
