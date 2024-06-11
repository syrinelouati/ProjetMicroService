package com.omaima.microcampplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication

public class MicroCampPlaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroCampPlaceApplication.class, args);
	}

}
