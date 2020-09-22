package com.example.Competence.CsvToMongo;

import com.example.Competence.Models.Competence;
import com.example.Competence.Repositories.CompetenceRepository;
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CsvToMongo {

    @Autowired
    CompetenceRepository competenceRepository;

    // convert csv dataset into a list (older version)

    public List<String> csvToList() {
        String compSkill = "";
        List<String> allComp = new ArrayList<>();
        String csvPath = "/home/akrem/Desktop/Plateforme-de-gestion-de-carrieres-et-de-formations/outils necessaire/computer_skills.csv";
        try {
            FileReader csvReader = new FileReader(csvPath);
            BufferedReader br = new BufferedReader(csvReader);
            while ((compSkill = br.readLine()) != null) {
                allComp.add(compSkill); //the list of all computer skills in the dataset
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allComp;

    }


    //convert the dataset collection into list (new version)

    public List<String> mongoToList() {
        List<String> allComp = new ArrayList<>();

        MongoClient mongoClient = MongoClients.create();
        MongoDatabase db = mongoClient.getDatabase("Competence_database");

        MongoCollection<Document> collection = db.getCollection("Competence");
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("title", "jabrane");
        FindIterable<Document> iterable = collection.find(whereQuery);
        for (Document document: iterable){
            allComp = (List<String>) document.get("dataset");//the list of all computer skills in the dataset
        }


        return allComp;
    }

}
