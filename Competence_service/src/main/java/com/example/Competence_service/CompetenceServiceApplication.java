package com.example.Competence_service;

import com.example.Competence_service.convertCsv.CsvToList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class CompetenceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompetenceServiceApplication.class, args);
		/*CsvToList csv = new CsvToList();
		List<String> list = new ArrayList<>();
		list.add("test111");
		list.add("test222");
		csv.updateCsvFile(list);*/



	}

}
