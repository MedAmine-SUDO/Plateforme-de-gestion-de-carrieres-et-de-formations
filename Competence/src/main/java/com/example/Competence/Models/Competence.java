package com.example.Competence.Models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection ="Competence")


public class Competence {
    @Id
    private String id;

    private String title;
    private List<String> dataset;
    public String getTitle() {

        return title;

    }

    public void setTitle(String title) {
        this.title = title;
    }




    public Competence( String title, List<String> dataset) {

        this.dataset = dataset;
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
