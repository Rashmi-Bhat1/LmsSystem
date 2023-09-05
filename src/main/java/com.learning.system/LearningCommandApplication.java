package com.learning.system;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@OpenAPIDefinition
public class LearningCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningCommandApplication.class, args);
	}

}
