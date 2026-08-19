package com.categorypostgresservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CategorypostgreshApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategorypostgreshApplication.class, args);
	}

}
