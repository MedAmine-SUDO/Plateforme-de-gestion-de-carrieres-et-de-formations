package com.carthageSolution.learningStyleTest.repository;

import com.carthageSolution.learningStyleTest.model.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TestRepository extends MongoRepository<Test, String> {
    Test findByNumTest(Integer num);
    List<Test> findAll();
}
