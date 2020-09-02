package com.carthageSolution.testNiveau.service;

import com.carthageSolution.testNiveau.model.Test;

import java.util.List;
import java.util.Optional;

public interface TestService {
    List<Test> findAll();
    Test findByNumTest(Integer num);
    Test findByUserId(String userId);
    void saveTest(Integer nbrQuestions);
    void deleteTest(String id);
    void deleteAll();
    void save(Test test);
    Optional<Test> findById(String id);
    void updateTest(Test test);
}
