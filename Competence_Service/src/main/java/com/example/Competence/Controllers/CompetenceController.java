package com.example.Competence.Controllers;


import com.example.Competence.CsvToMongo.CsvToMongo;
import com.example.Competence.Models.Competence;
import com.example.Competence.Models.Candidat;
import com.example.Competence.Repositories.CandidatRepository;
import com.example.Competence.Repositories.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/Competences")


public class CompetenceController {

    @Autowired
    CompetenceRepository competenceRepository;

    // add new dataset
    // Only do this once !!!!

    @PostMapping("/areYouSure?")
    public ResponseEntity<Competence> createCompetence(@RequestBody Competence competence){
        try {
            Competence c = new Competence(competence.getTitle(),competence.getDataset());

            Competence c_ = competenceRepository.save(c);
            return new ResponseEntity<>(c_, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // fill the database with all Competences from a csv file

    @PostMapping("/csvTomongo")
    public ResponseEntity<Competence> createCompetence1(@RequestBody Competence competence){
        try {
            Competence c = new Competence(competence.getTitle(),competence.getDataset());

            CsvToMongo csvToMongo = new CsvToMongo();
            List<String> list;
            list = csvToMongo.csvToList();

            c.setDataset(list);
            Competence c_ = competenceRepository.save(c);
            return new ResponseEntity<>(c_, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // list all the Competences in the database

    @GetMapping("")
    public List<Competence> getCompetence(){
        List<Competence> list = new ArrayList<>();
        competenceRepository.findAll().forEach(list::add);
        return list;
    }

    //update the dataset

    @PutMapping("/{id}")
    public Competence updateCompetence(@PathVariable("id") String id,@RequestBody Competence competence){
       Optional <Competence> c = competenceRepository.findById(id);

           Competence c_ = c.get();
           c_.setTitle(competence.getTitle());
           c_.setDataset(competence.getDataset());
           return competenceRepository.save(c_);
    }

    // delete by id

    @DeleteMapping("/{id}")
    public void deleteCompetence(@PathVariable("id") String id){
        competenceRepository.deleteById(id);
    }

    // delete all data

    @DeleteMapping("")
    public void deleteAll(){
        competenceRepository.deleteAll();
    }

}
