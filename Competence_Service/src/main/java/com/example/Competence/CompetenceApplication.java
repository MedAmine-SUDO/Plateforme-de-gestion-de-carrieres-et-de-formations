package com.example.Competence;

import com.example.Competence.CVreader.CvReader;
import com.example.Competence.CsvToMongo.CsvToMongo;
import com.mongodb.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CompetenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompetenceApplication.class, args);

		//test
		/*
		CvReader cvReader = new CvReader();
		List<String> list = new ArrayList<>();
		list = cvReader.readCV("/home/jabrane/Desktop/info1/Cv/cv_fr_medamine.pdf");*/

		/*CsvToMongo csvToMongo = new CsvToMongo();
		List<String> allComp ;
		allComp = csvToMongo.mongoToList();
		for(String comp: allComp){
			System.out.println(comp);
		}*/

	}

}
