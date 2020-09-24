package com.carthageSolution.testNiveau.service;

import com.carthageSolution.testNiveau.model.Result;
import com.carthageSolution.testNiveau.model.SkillLevel;

import java.util.List;
import java.util.Optional;

public interface ResultService {
    List<Result> findAll();
    List<Result> findByUserId(String userId);
    List<Result> findBySkillLevel(SkillLevel skillLevel);
    Optional<Result> findById(String id);
    void save(Result result);
    void deleteAll();
    void deleteByUserId(String userId);
}
