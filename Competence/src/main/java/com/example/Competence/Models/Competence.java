package com.example.Competence.Models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection ="Competence")


public class Competence {
    @Id
    private String id;

    private String title; //Nom du dataset
    private List<String> dataset; // dataset de tous les competences sous la forme d'une list
    public String getTitle() {

        return title;

    }

    //Constructor
    public Competence( String title, List<String> dataset) {

        this.dataset = dataset;
        this.title = title;
    }

    //getters and setters

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getDataset() {
        return dataset;
    }

    public void setDataset(List<String> dataset) {
        this.dataset = dataset;
    }



}
