package com.malek.microfeedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroFeedbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroFeedbackApplication.class, args);
    }

}
