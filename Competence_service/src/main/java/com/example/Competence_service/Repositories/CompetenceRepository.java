package com.example.Competence_service.Repositories;

import com.example.Competence_service.Models.Competence;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CompetenceRepository extends MongoRepository<Competence,String> {

    List<Competence> findByIdCandidat(String idCandidat);
    Competence findByid(String id);
}
