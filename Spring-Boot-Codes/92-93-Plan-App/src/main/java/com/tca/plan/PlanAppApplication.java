package com.tca.plan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PlanAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanAppApplication.class, args);
	}

}
