package com.Syrin.microproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroProductApplication.class, args);
	}

}
