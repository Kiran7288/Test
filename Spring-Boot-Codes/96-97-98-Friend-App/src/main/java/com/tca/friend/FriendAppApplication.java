package com.tca.friend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FriendAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendAppApplication.class, args);
	}

}