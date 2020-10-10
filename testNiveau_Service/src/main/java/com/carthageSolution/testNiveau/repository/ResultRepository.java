package com.carthageSolution.testNiveau.repository;

import com.carthageSolution.testNiveau.model.Result;
import com.carthageSolution.testNiveau.model.SkillLevel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResultRepository extends MongoRepository<Result, String> {
    List<Result> findAll();
    List<Result> findByUserId(String userId);
    List<Result> findBySkillLevel(SkillLevel skillLevel);
    void deleteByUserId(String userId);
}
