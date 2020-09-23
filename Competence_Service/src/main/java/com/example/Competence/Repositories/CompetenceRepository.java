package com.example.Competence.Repositories;

import com.example.Competence.Models.Competence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CompetenceRepository extends MongoRepository<Competence,String> {
    List<Competence> findAll();
    Competence findByid(String id);
}
