package com.example.Competence.Models;


import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection ="Competence_Candidat")
public class Candidat {


    @Id
    private String id;

    private String title; //nom du candidat
    private List<String> list; //List des Competences du candidat
    private Binary file; // le CV du Candidat en format pdf


    //Constructor

   /* public Candidat(String title) {
        this.title = title;

    }*/

    //getters and setters

    public Binary getFile() {
        return file;
    }

    public void setFile(Binary file) {
        this.file = file;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
