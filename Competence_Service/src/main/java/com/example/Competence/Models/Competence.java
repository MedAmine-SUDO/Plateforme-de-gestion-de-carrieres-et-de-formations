package com.example.Competence.Models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection ="Competence")


public class Competence {
    @Id
    private String id;


    private List<String> dataset; // dataset de tous les competences sous la forme d'une list


    //Constructor
    public Competence(  List<String> dataset) {

        this.dataset = dataset;

    }

    //getters and setters



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
