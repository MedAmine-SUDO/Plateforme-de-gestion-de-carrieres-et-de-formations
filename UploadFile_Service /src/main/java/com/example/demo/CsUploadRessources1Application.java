package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CsUploadRessources1Application {

	public static void main(String[] args) {
		SpringApplication.run(CsUploadRessources1Application.class, args);
		System.out.print("hello world from file upload");
	}

}
