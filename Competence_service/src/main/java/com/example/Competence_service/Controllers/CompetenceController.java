package com.example.Competence_service.Controllers;

import com.example.Competence_service.CvReader.CvReader;
import com.example.Competence_service.Models.Competence;
import com.example.Competence_service.Repositories.CompetenceRepository;
import com.example.Competence_service.convertCsv.CsvToList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/Competence")
public class CompetenceController {

    @Autowired
    CompetenceRepository competenceRepository;

    //add new Competence
    @PostMapping("")
    public ResponseEntity<Competence> add(@RequestBody Competence competence){
        try{
        Competence c = new Competence();
        c.setIdCandidat(competence.getIdCandidat());
        c.setList(competence.getList());
        Competence c_ = competenceRepository.save(c);
        //update the csv file

            /*
                to be added in the next commit
             */

            return new ResponseEntity<>(c_, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cv")
    public ResponseEntity<Competence> addfromcv(@RequestParam("idCandidat") String idCandidat,
                                                @RequestParam("file") MultipartFile file ){
        try{
            Competence c = new Competence();
            c.setIdCandidat(idCandidat);

            //call the detection algorithm
            CvReader cvReader = new CvReader();
            List<String> list = cvReader.readCV(file);

            c.setList(list);
            Competence c_ = competenceRepository.save(c);
            return new ResponseEntity<>(c_, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // list all the Competences in the database

    @GetMapping("")
    public List<Competence> getall(){
        List<Competence> list = new ArrayList<>();
        competenceRepository.findAll().forEach(list::add);
        return list;
    }

    //Get one candidate's competence from his id
    @GetMapping("/{idCandiat}")
    public Competence getone(@PathVariable("idCandidat") String idCandidat){
        Competence c = competenceRepository.findByIdCandidat(idCandidat);
        return c;
    }

    //get one candidate's competence by the competence id
    @GetMapping("/{id}")
    public Optional<Competence> getOneByid(@PathVariable("id") String id){
        Optional<Competence> c = competenceRepository.findById(id);
        return c;
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
