package com.formationdataservice.tn.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.formationdataservice.tn.models.Formation;

public interface FormationRepository extends MongoRepository<Formation, String> {

}
