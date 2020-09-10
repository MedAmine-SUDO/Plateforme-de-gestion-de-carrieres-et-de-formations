package com.example.Competence.CVreader;

import com.example.Competence.Components.CompetenceRepositoryImpl;
import com.example.Competence.Controllers.CompetenceController;
import com.example.Competence.CsvToMongo.CsvToMongo;
import com.example.Competence.Models.Competence;
import com.example.Competence.Repositories.CompetenceRepository;
import com.example.Competence.Services.CompetenceService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class CvReader {

    @Autowired
    CompetenceRepositoryImpl competenceRepository;
    public List<String> readCV(String url) {

        //read pdf file

        PDDocument pd;
        BufferedWriter wr;
        try {
            File input = new File(url);  // The PDF file from where you would like to extract
            File output = new File("/home/jabrane/Desktop/info1/Cv/res.txt"); // The text file where you are going to store the extracted data
            pd = PDDocument.load(input);
            System.out.println(pd.getNumberOfPages());
            System.out.println(pd.isEncrypted());
            pd.save("CopyOfBill.pdf"); // Creates a copy called "CopyOfInvoice.pdf"
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(1); //Start extracting from page 3
            stripper.setEndPage(1); //Extract till page 5
            wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
            stripper.writeText(pd, wr);
            if (pd != null) {
                pd.close();
            }
            // I use close() to flush the stream.
            wr.close();
            } catch (Exception e) {
            e.printStackTrace();
            }

        //list all the words in the candidate's resume

        String[] words;
        String content = "";
        String path = "/home/jabrane/Desktop/info1/Cv/res.txt";
        try {
            content = Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        words = content.split(" ");
        ArrayList<String> compList = new ArrayList<>(Arrays.asList(words)); //the list of words


        //read dataset of all computer skills
        //convert csv file into list

        CsvToMongo csvToMongo = new CsvToMongo();
        List<String> allComp ;
        allComp = csvToMongo.csvToList();

        //read Dataset from mongodb

       /*List<String> allComp ;
       allComp = competenceRepository.getdataset();*/






        // register results

        ArrayList<String> computerSkills = new ArrayList<>();
        ArrayList<String> cr = new ArrayList<>();
        for (String com1 : allComp) {
            for (String com2 : compList) {
                if (com1.equalsIgnoreCase(com2)) {
                    computerSkills.add(com2); //list of the candidate computer skills
                }
            }
        }

        //removing duplicate elements

        Set<String> hashSet = new LinkedHashSet(computerSkills);
        List<String> cs = new ArrayList(hashSet);

        //test result

        for (String s : cs) {
            System.out.println("my skills are: " + s);
        }

        return cs;


    }
}