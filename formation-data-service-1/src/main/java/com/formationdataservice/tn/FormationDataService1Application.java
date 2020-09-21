package com.formationdataservice.tn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class FormationDataService1Application {

	public static void main(String[] args) {
		SpringApplication.run(FormationDataService1Application.class, args);
	}

}
