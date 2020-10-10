package com.example.Competence_service.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection ="candidat_competences")
public class Competence {

    @Id
    private String id;
    private List<String> list;

    public void setId(String id) {
        this.id = id;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public List<String> getList() {
        return list;
    }



}
