package com.carthageSolution.learningStyleTest.service;

import com.carthageSolution.learningStyleTest.model.Test;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface TestService {
    List<Test> findAll();
    Test findByNumTest(Integer num);
    void saveTest(Integer nbr);
    void deleteTest(String id);
    void deleteAll();
    void save(Test test);
    public Optional<Test> findById(String id);
    public void updateTest(Test test);
}
