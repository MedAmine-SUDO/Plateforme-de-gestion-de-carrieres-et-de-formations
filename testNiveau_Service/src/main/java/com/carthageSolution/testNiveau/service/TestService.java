package com.carthageSolution.testNiveau.service;

import com.carthageSolution.testNiveau.model.Test;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface TestService {
    List<Test> findAll();
    Test findByNumTest(Long num);
    List<Test> findByUserId(String userId);
    Optional<Test> findById(String id);
    Test saveTest();
    void save(Test test);
    void deleteTest(String id);
    void deleteAll();
    void updateTest(Test test);
    HashMap<String, Integer> generateResult(String id);
}
