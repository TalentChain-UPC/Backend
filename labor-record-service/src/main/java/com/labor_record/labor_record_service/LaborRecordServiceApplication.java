package com.labor_record.labor_record_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableDiscoveryClient
@EnableJpaAuditing
@SpringBootApplication
public class LaborRecordServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaborRecordServiceApplication.class, args);
	}

}
