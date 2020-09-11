package com.example.Competence.CVreader;

import com.example.Competence.CsvToMongo.CsvToMongo;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.springframework.core.io.ClassPathResource;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;

import java.util.*;





public class CvReader {



    // method to convert Mutipartfile to a File

    public  File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }



    public List<String> readCV(MultipartFile pdfFile) {

        //read pdf file

        PDDocument pd;
        BufferedWriter wr;
        try {

            // The PDF file from where you would like to extract
            File pdf;
            pdf = this.convert(pdfFile);


            // The text file where you are going to store the extracted data (select file)

            File file = new ClassPathResource("res.txt").getFile();


            //load the pdf file

            pd = PDDocument.load(pdf);
            System.out.println(pd.getNumberOfPages());
            System.out.println(pd.isEncrypted());
            pd.save("CopyOfBill.pdf"); // Creates a copy called "CopyOfInvoice.pdf"
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(1); //Start extracting from page 3
            stripper.setEndPage(1); //Extract till page 5

            //write data in res.txt

            wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
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


        try {

           //read res.txt and extract data (read)
            File file = new ClassPathResource("res.txt").getFile();
            content = new String(Files.readAllBytes(file.toPath()));


        }catch (Exception e) {
            e.printStackTrace();
        }

      /* //String path = "/home/jabrane/Desktop/info1/Cv/res.txt";
        try {
            content = Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }*/


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