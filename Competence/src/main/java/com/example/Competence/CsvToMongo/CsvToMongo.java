package com.example.Competence.CsvToMongo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvToMongo {

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
}
