package com.example.Competence.Repositories;

import com.example.Competence.Models.Candidat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CandidatRepository extends MongoRepository<Candidat,String> {
    List<Candidat> findAll();
}
