package com.example.Competence_service.convertCsv;

import com.opencsv.CSVWriter;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

import java.util.*;

public class CsvToList {

    //older version
   /* public List<String> csvToList() {
        String compSkill = "";
        List<String> allComp = new ArrayList<>();

        String csvPath = "/home/jabrane/Desktop/github/Plateforme-de-gestion-de-carrieres-et-de-formations/outils necessaire/computer_skills.csv";
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

    }*/
    //new version
    public List<String> csvToList2(){
        String compSkill = "";
        List<String> allComp = new ArrayList<>();
        try {

            //read computer_skills.csv from resources file
            File file = new ClassPathResource("computer_skills.csv").getFile();
            FileReader csvReader = new FileReader(file);
            BufferedReader br = new BufferedReader(csvReader);
            while ((compSkill = br.readLine()) != null) {
                allComp.add(compSkill); //the list of all computer skills
            }


        }catch (Exception e) {
            e.printStackTrace();
        }
        return  allComp;
    }

    //update the csv file with the new competences
    public void updateCsvFile(List<String> list) {

        //try number 1
       /* String compSkill = "";
        List<String> allComp = new ArrayList<>();

        try {

            //read computer_skills.csv from resources file
            File file = new ClassPathResource("computer_skills.csv").getFile();

            FileReader csvReader = new FileReader(file);
            BufferedReader br = new BufferedReader(csvReader);
            while ((compSkill = br.readLine()) != null) {
                allComp.add(compSkill); //the list of all computer skills
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String s : list) {
            if (!allComp.contains(s)) {

                allComp.add(s);

            }
        }
        //update computerskills.csv
            try {
                File file1 = new ClassPathResource("computer_skills.csv").getFile();

                FileWriter fileWriter = new FileWriter(file1,true);
                for(String s: allComp){
                    fileWriter.write(s);
                }
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        //try number 2
        /*String skill;
        try {
            File file1 = new ClassPathResource("computer_skills.csv").getFile();
            FileReader csvReader = new FileReader(file1);
            BufferedReader br = new BufferedReader(csvReader);
            FileWriter writer = new FileWriter(file1,true);
            BufferedWriter out = new BufferedWriter(writer);


            while ((skill = br.readLine()) != null ) {

                for(String s :list) {

                        if (!skill.equals(s)) {

                            writer.write(s);
                            //out.write(s);
                            //out.newline(s);

                        }
                    }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //try number 3






    }
}
