package com.example.Competence.Controllers;


import com.example.Competence.CVreader.CvReader;
import com.example.Competence.Models.Candidat;
import com.example.Competence.Models.Competence;
import com.example.Competence.Repositories.CandidatRepository;
import com.example.Competence.Repositories.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/Candidat")
public class CandidatController {

    @Autowired
    CandidatRepository candidatRepository;

    @Autowired
    CompetenceRepository competenceRepository;

    @PostMapping("")
    public ResponseEntity<Candidat> addCandidat(@RequestBody Candidat candidat){
        try {
            Candidat c = new Candidat(candidat.getTitle(),candidat.getList());
            List<String> listCandidat ;
            listCandidat = candidat.getList();

            Candidat c_ = candidatRepository.save(c);
            List<Competence> competences = competenceRepository.findAll();
            List<String> listComp =new ArrayList<>();

            for(Competence comp:competences){
                listComp.addAll(comp.getDataset());
            }

                for(String s2:listCandidat){
                    if(!(listComp.contains(s2))){
                        //add competences  to a list
                        listComp.add(s2);

                    }
                }
            for(Competence comp:competences){
                comp.setDataset(listComp);
                // update the mongo database with the new Competence
                competenceRepository.save(comp);
            }
            return new ResponseEntity<>(c_, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/cv")
    public ResponseEntity<Candidat> addCandidatfromcv(@RequestBody Candidat candidat){
        try {
            Candidat c = new Candidat(candidat.getTitle(),candidat.getList());
            List<String> list ;
            CvReader cvReader = new CvReader();
            list=cvReader.readCV("/home/jabrane/Desktop/info1/Cv/cv_fr_medamine.pdf");
            c.setList(list);
            c.setTitle("aminoss");

            Candidat c_ = candidatRepository.save(c);
            return new ResponseEntity<>(c_, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("")
    public List<Candidat> getCandidat(){
        List<Candidat> list = new ArrayList<>();
        candidatRepository.findAll().forEach(list::add);
        return list;
    }
}
