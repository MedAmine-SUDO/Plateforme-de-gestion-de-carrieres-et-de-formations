package com.example.Competence.CsvToMongo;

import com.example.Competence.Models.Competence;
import com.example.Competence.Repositories.CompetenceRepository;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.Document;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvToMongo {

    @Autowired
    CompetenceRepository competenceRepository;
    //method to convert csv dataset into a list
    public List<String> csvToList(){
        String compSkill ="";
        List<String> allComp = new ArrayList<>();
        String csvPath = "/home/jabrane/Desktop/info1/Cv/computer_skills.csv";
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

    //method to convert competence collection into list
  /*  public List<String> mongoToList(){
        List<String> allComp = new ArrayList<>();
        @Deprecated
        Mongo mongo = new Mongo("localhost", 27017);
        @Deprecated
        DB db = mongo.getDB("Competence_database");
        DBCollection collection = db.getCollection("Competence");

        BasicDBObject query = new BasicDBObject();
        BasicDBObject fields = new BasicDBObject("dataset", 1);

        DBCursor dbCursor= collection.find(query,fields);

        while (dbCursor.hasNext()){
            System.out.println(dbCursor.next());
        }

    }*/

}
