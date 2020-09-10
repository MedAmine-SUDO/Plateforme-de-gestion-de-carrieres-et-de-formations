package com.example.Competence.Components;


import com.example.Competence.CompetenceApplication;
import com.example.Competence.Models.Competence;
import com.example.Competence.Repositories.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompetenceRepositoryImpl {

    @Autowired
    CompetenceRepository competenceRepository;

    @SuppressWarnings("unused")
    public List<String> getdataset(){
        List<String> list = new ArrayList<>();
        List<Competence> c = competenceRepository.findAll();
        for (Competence c1:c){
            list=c1.getDataset();
        }

        return  list;
    }

}
