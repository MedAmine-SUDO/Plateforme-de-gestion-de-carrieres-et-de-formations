package com.carthageSolution.testNiveau.repository;

import com.carthageSolution.testNiveau.model.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TestRepository extends MongoRepository<Test, String> {
    Test findByNumTest(Long num);
    List<Test> findByUserId(String userId);
    List<Test> findAll();
}
