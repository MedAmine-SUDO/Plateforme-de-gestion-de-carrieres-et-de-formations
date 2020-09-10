package com.example.Competence.Services;


import com.example.Competence.Models.Competence;
import com.example.Competence.Repositories.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompetenceService {
    @Autowired
    CompetenceRepository competenceRepository;
    public List<String> getdataset(){
        List<String> allComp = new ArrayList<>();
        List<Competence> c = competenceRepository.findAll();
        for (Competence c1:c){
            allComp=c1.getDataset();
        }
        return allComp;
    }
}
