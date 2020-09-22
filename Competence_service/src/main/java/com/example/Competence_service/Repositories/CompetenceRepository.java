package com.example.Competence_service.Repositories;

import com.example.Competence_service.Models.Competence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompetenceRepository extends MongoRepository<Competence,String> {

    Competence findByIdCandidat(String idCandidat);
}
