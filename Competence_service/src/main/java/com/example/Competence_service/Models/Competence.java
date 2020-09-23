package com.example.Competence_service.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection ="candidat_competences")
public class Competence {

    @Id
    private String id;
    private String idCandidat;
    private List<String> list;

    public void setId(String id) {
        this.id = id;
    }

    public void setIdCandidat(String idCandidat) {
        this.idCandidat = idCandidat;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public String getIdCandidat() {
        return idCandidat;
    }

    public List<String> getList() {
        return list;
    }



}
