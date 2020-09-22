package com.carthageSolution.learningStyleTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LearningStyleTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(LearningStyleTestApplication.class, args);
	}
}
